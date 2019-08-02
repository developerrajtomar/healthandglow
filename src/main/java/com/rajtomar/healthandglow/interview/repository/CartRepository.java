package com.rajtomar.healthandglow.interview.repository;

import com.rajtomar.healthandglow.interview.model.Cart;
import com.rajtomar.healthandglow.interview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository which deals with the persistent related tasks to Carts.
 *
 * @author Raj Tomar
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

}
