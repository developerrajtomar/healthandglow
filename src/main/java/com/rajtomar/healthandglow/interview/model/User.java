package com.rajtomar.healthandglow.interview.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;

/**
 * A java object representation of user table in the DB. This should contain all the fields
 * related to columns in the DB.
 *
 * @author Raj Tomar
 */
@Data
@Entity
@FieldDefaults(level = PRIVATE)
public class User implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false)
    String name;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true, nullable = false)
    String email;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(nullable = false)
    String password;

    String phone;

    @OneToOne
    Cart cart;

}
