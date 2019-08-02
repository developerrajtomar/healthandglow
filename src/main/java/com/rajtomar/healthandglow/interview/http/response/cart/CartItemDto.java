package com.rajtomar.healthandglow.interview.http.response.cart;

import com.rajtomar.healthandglow.interview.http.common.AbstractCartItemDto;
import com.rajtomar.healthandglow.interview.http.response.AbstractResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Response contract for the cart item.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class CartItemDto extends AbstractCartItemDto implements AbstractResponseDto {

    double aggregatedPrice;

}
