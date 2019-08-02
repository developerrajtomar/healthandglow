package com.rajtomar.healthandglow.interview.model.embeddable;

import com.rajtomar.healthandglow.interview.model.Cart;
import com.rajtomar.healthandglow.interview.model.Product;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A java object represent a composite key inside DB table cart_item. This just give basic
 * overview of how to create a composite key in java object.
 *
 * @author Raj Tomar
 */
@Data
@ToString
@Embeddable
public class CartItemId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    Cart cart;

    @OneToOne
    Product product;

    @Override
    public int hashCode() {
        return Objects.hash(cart.getId(), product.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId)) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(cart.getId(), that.cart.getId()) &&
               Objects.equals(product.getId(), that.product.getId());
    }

}
