package com.rajtomar.healthandglow.interview.http.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

/**
 * Basic contract fields required for request/response related to product.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractProductDto extends AbstractDto {

    @NotNull(message = "{notnull.name}")
    @Size(min = 1, max = 100)
    String name;

    @NotNull
    @Size(min = 20, max = 500)
    String description;

    @Size(max = 255, message = "{length.max}")
    String shortDescription;

    @Positive(message = "mrp {positive}")
    double mrp;

    @PositiveOrZero
    int quantity;

}
