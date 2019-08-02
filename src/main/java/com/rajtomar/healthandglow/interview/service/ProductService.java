package com.rajtomar.healthandglow.interview.service;

import com.rajtomar.healthandglow.interview.error.DataDoesNotExistsException;
import com.rajtomar.healthandglow.interview.http.response.product.ProductDto;
import com.rajtomar.healthandglow.interview.model.Product;
import com.rajtomar.healthandglow.interview.repository.ProductRepository;
import com.rajtomar.healthandglow.interview.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.CONTENT_FOUND_LOG_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.GenericMessage.NO_CONTENT_LOG_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.PRODUCT;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.getRequestType;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.noContentForTypeAndIdMessage;

/**
 * Service class which deals in the operations related to Products.
 *
 * @author Raj Tomar
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public static Product convertToProduct(
            com.rajtomar.healthandglow.interview.http.request.product.ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setShortDescription(productDto.getShortDescription());
        product.setMrp(productDto.getMrp());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    public static Product convertToProduct(
            ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setShortDescription(productDto.getShortDescription());
        product.setMrp(productDto.getMrp());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    public static ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setShortDescription(product.getShortDescription());
        productDto.setMrp(product.getMrp());
        productDto.setQuantity(product.getQuantity());
        return productDto;
    }

    public Product retrieveProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            log.debug(CONTENT_FOUND_LOG_MESSAGE, getRequestType(PRODUCT), productId);
            return product.get();
        }
        log.error(NO_CONTENT_LOG_MESSAGE, getRequestType(PRODUCT), productId);
        throw new DataDoesNotExistsException(noContentForTypeAndIdMessage(PRODUCT, productId));
    }

    public ProductDto retrieveProductDto(Long productId) {
        return convertToProductDto(retrieveProduct(productId));
    }

    public Set<ProductDto> retrieveAllProducts() {
        return productRepository.findAll()
                                .parallelStream()
                                .map(ProductService::convertToProductDto)
                                .collect(Collectors.toSet());
    }

    public ProductDto createProduct(
            @NotNull com.rajtomar.healthandglow.interview.http.request.product.ProductDto productDto) {
        ObjectUtils.assertNull(productDto);
        return convertToProductDto(productRepository.save(convertToProduct(productDto)));
    }
}
