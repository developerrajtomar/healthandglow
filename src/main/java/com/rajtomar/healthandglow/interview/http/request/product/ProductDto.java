package com.rajtomar.healthandglow.interview.http.request.product;

import com.rajtomar.healthandglow.interview.http.common.AbstractProductDto;
import com.rajtomar.healthandglow.interview.http.request.AbstractRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Basic request contract for the product controller.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends AbstractProductDto implements AbstractRequestDto {

}
