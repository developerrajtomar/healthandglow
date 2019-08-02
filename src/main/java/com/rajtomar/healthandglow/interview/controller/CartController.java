package com.rajtomar.healthandglow.interview.controller;

import com.rajtomar.healthandglow.interview.http.request.cart.CartItemDto;
import com.rajtomar.healthandglow.interview.http.request.cart.RemoveCartItemDto;
import com.rajtomar.healthandglow.interview.http.response.GenericResponse;
import com.rajtomar.healthandglow.interview.http.response.cart.CartDto;
import com.rajtomar.healthandglow.interview.model.User;
import com.rajtomar.healthandglow.interview.service.CartService;
import com.rajtomar.healthandglow.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.*;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.CART;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.formatMessage;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.generateResponse;
import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.*;

/**
 * Cart Request controller. This is not the enhanced one but it serve the request which was the
 * part of the given task.
 *
 * @author Raj Tomar
 */
@Slf4j
@RestController
@RequestMapping(CART)
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;
    final UserService userService;

    /**
     * Retrieval of the cart for the given user.
     *
     * @return response with the cart details.
     */
    @GetMapping
    public GenericResponse getCart() {
        /* Actually we need to fetch userId from login token or session. */
        User user = userService.retrieveUser(1L);
        CartDto cartDto = cartService.retrieveCart(user);
        return generateResponse(OK, formatMessage(GET_RESPONSE_MESSAGE, "cart",
                                                  valueOf(cartDto.getId())), cartDto);
    }

    /**
     * Add item into the cart for the given user.
     *
     * @return response with the updated cart details.
     */
    @PostMapping
    public GenericResponse addItemInCart(@RequestBody @Validated CartItemDto cartItemDto) {
        /* Actually we need to retrieve user from token or session. */
        User user = userService.retrieveUser(1L);
        CartDto dto = cartService.addItemInCart(user, cartItemDto);
        return generateResponse(CREATED, formatMessage(CREATED_RESPONSE_MESSAGE, "CartItem",
                                                       valueOf(dto.getId())), dto);
    }

    /**
     * Remove items from the cart for the given user.
     *
     * @return response with the updated cart details.
     */
    @PutMapping
    public GenericResponse removeItemFromCart(
            @RequestBody @Validated RemoveCartItemDto cartItemDto) {
        /* Actually we need to retrieve user from token or session. */
        User user = userService.retrieveUser(1L);
        cartService.removeItemFromCart(user, cartItemDto);
        return generateResponse(ACCEPTED, formatMessage(UPDATE_RESPONSE_MESSAGE, "Cart"));
    }

}
