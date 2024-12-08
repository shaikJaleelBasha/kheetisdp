package com.sdk.kheeti.repositories;

import com.sdk.kheeti.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Fetch all orders placed by a specific user.
     *
     * @param userId The user's ID.
     * @return A list of orders.
     */
    List<Order> findByUserId(Long userId);
}
