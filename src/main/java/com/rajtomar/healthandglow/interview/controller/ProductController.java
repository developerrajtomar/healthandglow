package com.rajtomar.healthandglow.interview.controller;

import com.rajtomar.healthandglow.interview.http.response.GenericResponse;
import com.rajtomar.healthandglow.interview.http.response.product.ProductDto;
import com.rajtomar.healthandglow.interview.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.GET_RESPONSE_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.GenericMessage.LIST_RESPONSE_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.PRODUCT;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.formatMessage;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.generateResponse;
import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.OK;

/**
 * Product Request controller. This is not an ask, but needed to add data in order to serve other
 * requests.
 *
 * @author Raj Tomar
 */
@Slf4j
@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    /**
     * Creates a product into the system.
     *
     * @return response with the product details.
     */
    @PostMapping
    public GenericResponse create(
            @RequestBody @Validated
                    com.rajtomar.healthandglow.interview.http.request.product.ProductDto productDto) {
        ProductDto createdProductDto = productService.createProduct(productDto);
        return generateResponse(OK, formatMessage(GET_RESPONSE_MESSAGE, "product",
                                                  valueOf(createdProductDto.getId())),
                                createdProductDto);
    }

    /**
      * Retrieval of the product associated with the provided product id.
      *
      * @return response with the product details.
      */
    @GetMapping("/{productId}")
    public GenericResponse getSingleProduct(@PathVariable Long productId) {
        ProductDto productDto = productService.retrieveProductDto(productId);
        return generateResponse(OK, formatMessage(GET_RESPONSE_MESSAGE, "product",
                                                  valueOf(productDto.getId())), productDto);
    }

    /**
     * Retrieves list of products available in the system.
     *
     * @return response with the product details.
     */
    @GetMapping
    public GenericResponse getAllProducts() {
        Set<ProductDto> productDtos = productService.retrieveAllProducts();
        return generateResponse(OK, formatMessage(LIST_RESPONSE_MESSAGE, "products"), productDtos);
    }

}
