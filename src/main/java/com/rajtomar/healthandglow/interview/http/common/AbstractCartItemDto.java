package com.rajtomar.healthandglow.interview.http.common;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static lombok.AccessLevel.PRIVATE;

/**
 * Basic contract fields required for request/response related to cart item.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
public abstract class AbstractCartItemDto {

    @Valid
    @Positive
    int quantity;

    @Valid
    @NotNull
    @Positive
    Long productId;

}
