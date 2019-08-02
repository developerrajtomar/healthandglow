package com.rajtomar.healthandglow.interview.service;

import com.rajtomar.healthandglow.interview.error.DataDoesNotExistsException;
import com.rajtomar.healthandglow.interview.error.cart.ItemRemovalFromEmptyCartException;
import com.rajtomar.healthandglow.interview.error.product.ProductQuantityOutOfStockException;
import com.rajtomar.healthandglow.interview.http.request.cart.RemoveCartItemDto;
import com.rajtomar.healthandglow.interview.http.response.cart.CartDto;
import com.rajtomar.healthandglow.interview.http.response.cart.CartItemDto;
import com.rajtomar.healthandglow.interview.model.Cart;
import com.rajtomar.healthandglow.interview.model.CartItem;
import com.rajtomar.healthandglow.interview.model.Product;
import com.rajtomar.healthandglow.interview.model.User;
import com.rajtomar.healthandglow.interview.repository.CartRepository;
import com.rajtomar.healthandglow.interview.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.CONTENT_FOUND_LOG_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.GenericMessage.NO_CONTENT_LOG_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.CART;
import static com.rajtomar.healthandglow.interview.service.ProductService.convertToProduct;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.*;

/**
 * Service class which deals in the operations related to Carts.
 *
 * @author Raj Tomar
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    final UserService userService;
    final EntityManager entityManager;
    final CartRepository cartRepository;
    final ProductService productService;

    public CartDto retrieveCart(@NotNull User user) {
        ObjectUtils.assertNull(user);
        Optional<Cart> cart = cartRepository.findByUser(user);
        if (cart.isPresent()) {
            log.debug(CONTENT_FOUND_LOG_MESSAGE, getRequestType(CART), user);
            return convertToCartDto(cart.get());
        }
        log.error(NO_CONTENT_LOG_MESSAGE, getRequestType(CART), user);
        throw new DataDoesNotExistsException(noContentForTypeAndIdMessage(CART, user.getId()));
    }

    @Transactional
    public CartDto addItemInCart(@NotNull User user,
                                 @NotNull com.rajtomar.healthandglow.interview.http.request.cart.CartItemDto cartItemDto) {
        ObjectUtils.assertNull(user);
        ObjectUtils.assertNull(cartItemDto);
        Optional<Cart> cartOptional = cartRepository.findByUser(user);
        Cart cart = cartOptional.orElseGet(Cart::new);
        Optional<CartItem> cartItemOptional = cart.getItems()
                                                  .parallelStream()
                                                  .filter(item -> item.getCartItemId().getProduct()
                                                                      .getId()
                                                                      .equals(cartItemDto
                                                                                      .getProductId()))
                                                  .findFirst();
        CartItem cartItem = cartItemOptional.orElseGet(CartItem::new);
        Product product = convertToProduct(
                productService.retrieveProductDto(cartItemDto.getProductId()));
        final int itemQuantity = cartItem.getQuantity() + cartItemDto.getQuantity();
        if (product.getQuantity() < itemQuantity) {
            throw new ProductQuantityOutOfStockException(
                    "Required quantity:: " + itemQuantity + ", available quantity:: " +
                    product.getQuantity());
        }
        cartItem.getCartItemId().setProduct(product);
        cartItem.setQuantity(itemQuantity);
        cart.setUser(user);
        cartItem.getCartItemId().setCart(cart);
        cart.getItems().add(cartItem);
        cartRepository.save(cart);
        return convertToCartDto(cart);
    }

    @Transactional
    public void removeItemFromCart(@NotNull final User user,
                                   @NotNull final RemoveCartItemDto removeCartItemDto) {
        ObjectUtils.assertNull(user);
        ObjectUtils.assertNull(removeCartItemDto);
        Optional<Cart> cartOptional = cartRepository.findByUser(user);
        if (!cartOptional.isPresent()) {
            throw new ItemRemovalFromEmptyCartException("Can not remove item from an empty cart.");
        }
        Cart cart = cartOptional.get();
        Set<CartItem> cartItems = cart.getItems();
        cartItems.removeIf(cartItem -> removeIfRequired(cartItem, removeCartItemDto));
        cartItems.parallelStream()
                 .filter(item -> item.getCartItemId().getProduct()
                                     .getId().equals(removeCartItemDto
                                                             .getProductId()))
                 .findFirst()
                 .ifPresent(cartItem -> cartItem
                         .setQuantity(cartItem.getQuantity() - removeCartItemDto.getQuantity()));
        cartRepository.save(cart);
    }

    private boolean removeIfRequired(CartItem cartItem, RemoveCartItemDto removeCartItemDto) {
        return (cartItem.getCartItemId().getProduct().getId().equals(
                removeCartItemDto.getProductId()))
               && (cartItem.getQuantity() <= removeCartItemDto.getQuantity());
    }

    private CartDto convertToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUser().getId());
        cartDto.getCartItems().addAll(convertToCartItemDto(cart.getItems()));
        cartDto.setTotalAmount(cartDto.getCartItems()
                                      .parallelStream()
                                      .filter(dto -> dto instanceof com.rajtomar.healthandglow.interview.http.response.cart.CartItemDto)
                                      .map(dto -> (com.rajtomar.healthandglow.interview.http.response.cart.CartItemDto) dto)
                                      .map(com.rajtomar.healthandglow.interview.http.response.cart.CartItemDto::getAggregatedPrice)
                                      .reduce(0d, Double::sum));
        return cartDto;
    }

    private Set<CartItemDto> convertToCartItemDto(Collection<CartItem> items) {
        return items.parallelStream()
                    .map(this::convertToDartItemDto)
                    .collect(Collectors.toSet());
    }

    private CartItemDto convertToDartItemDto(CartItem item) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setQuantity(item.getQuantity());
        cartItemDto.setProductId(item.getCartItemId().getProduct().getId());
        cartItemDto.setAggregatedPrice(
                getSellingPrice(item.getCartItemId().getProduct()) * item.getQuantity());
        return cartItemDto;
    }

}
