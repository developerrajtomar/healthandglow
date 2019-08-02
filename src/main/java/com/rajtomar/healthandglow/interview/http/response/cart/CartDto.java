package com.rajtomar.healthandglow.interview.http.response.cart;

import com.rajtomar.healthandglow.interview.http.common.AbstractCartDto;
import com.rajtomar.healthandglow.interview.http.response.AbstractResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Response contract for the Cart.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class CartDto extends AbstractCartDto implements AbstractResponseDto {

    double aggregatedPrice;

}
