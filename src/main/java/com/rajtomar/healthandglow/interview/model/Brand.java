package com.rajtomar.healthandglow.interview.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

/**
 * A java object representation of Brand table in the DB. This is just to show the relation with
 * product, nothing we are doing as this is just an interview task application.
 *
 * @author Raj Tomar
 */
@Data
@Entity
@FieldDefaults(level = PRIVATE)
public class Brand implements IdentifiableEntity {

    @Id
    Long id;

    @OneToMany
    Set<Product> product;

}
