package com.rajtomar.healthandglow.interview.repository;

import com.rajtomar.healthandglow.interview.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository which deals with the persistent related tasks to Users.
 *
 * @author Raj Tomar
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
