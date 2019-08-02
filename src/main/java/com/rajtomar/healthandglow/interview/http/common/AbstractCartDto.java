package com.rajtomar.healthandglow.interview.http.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

/**
 * Basic contract fields required for request/response related to cart.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCartDto extends AbstractDto {

    Long userId;
    Set<AbstractCartItemDto> cartItems = new HashSet<>();
    double totalAmount;

}
