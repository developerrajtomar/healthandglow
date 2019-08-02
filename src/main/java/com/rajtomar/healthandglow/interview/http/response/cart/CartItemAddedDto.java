package com.rajtomar.healthandglow.interview.http.response.cart;

import com.rajtomar.healthandglow.interview.http.response.AbstractResponseDto;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class CartItemAddedDto implements AbstractResponseDto {

    Long cartId;
    Long addedCartItemId;

}
