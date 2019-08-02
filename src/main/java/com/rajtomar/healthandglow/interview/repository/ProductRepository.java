package com.rajtomar.healthandglow.interview.repository;

import com.rajtomar.healthandglow.interview.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository which deals with the persistent related tasks to Products.
 *
 * @author Raj Tomar
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
