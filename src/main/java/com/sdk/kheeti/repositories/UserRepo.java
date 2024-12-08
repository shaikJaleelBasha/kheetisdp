package com.sdk.kheeti.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdk.kheeti.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Find a user by their email.
     *
     * @param email the user's email
     * @return an Optional containing the User if found, or empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by their username.
     *
     * @param username the user's username
     * @return an Optional containing the User if found, or empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if a user exists by their username.
     *
     * @param username the user's username
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Check if a user exists by their email.
     *
     * @param email the user's email
     * @return true if the user exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Find a user by their phone number.
     *
     * @param phone the user's phone number
     * @return an Optional containing the User if found, or empty otherwise
     */
    Optional<User> findByPhone(String phone);
}
