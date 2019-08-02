package com.rajtomar.healthandglow.interview.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;

/**
 * A java object representation of product table in the DB. This should contain all the fields
 * related to columns in the DB. These are test fields and we should add many more fields here
 * but for testing these are fine enough.
 *
 * @author Raj Tomar
 */
@Data
@Entity
@FieldDefaults(level = PRIVATE)
public class Product implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, unique = true)
    String name;

    String model;

    @ManyToOne
    Brand brand;

    @NotNull
    @Size(min = 20, max = 500)
    String description;

    @Size(max = 255)
    String shortDescription;

    @PositiveOrZero
    double mrp;

    @PositiveOrZero
    double discount = 0;

    @PositiveOrZero
    int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    Cart cart;

}
