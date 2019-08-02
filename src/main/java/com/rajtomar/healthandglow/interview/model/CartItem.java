package com.rajtomar.healthandglow.interview.model;

import com.rajtomar.healthandglow.interview.model.embeddable.CartItemId;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

/**
 * A java object representation of cart_item table in the DB. This should contain all the fields
 * related to columns in the DB.
 *
 * @author Raj Tomar
 */
@Data
@Entity
@FieldDefaults(level = PRIVATE)
public class CartItem implements AbstractEntity {

    @EmbeddedId
    CartItemId cartItemId = new CartItemId();

    int quantity;

    LocalDateTime whenAdded;

}
