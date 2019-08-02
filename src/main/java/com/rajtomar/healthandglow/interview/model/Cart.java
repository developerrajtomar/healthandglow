package com.rajtomar.healthandglow.interview.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;

/**
 * A java object representation of cart table in the DB. This should contain all the fields
 * related to columns in the DB.
 *
 * @author Raj Tomar
 */
@Data
@Entity
@FieldDefaults(level = PRIVATE)
public class Cart implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartItemId.cart", orphanRemoval = true)
    Set<CartItem> items = new HashSet<>();

    @OneToOne
    User user;

}
