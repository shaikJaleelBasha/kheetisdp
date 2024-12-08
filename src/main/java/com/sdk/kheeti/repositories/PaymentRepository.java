package com.sdk.kheeti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdk.kheeti.controller.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Find all payments for a specific user.
     *
     * @param userId The ID of the user.
     * @return List of payments made by the user.
     */
    List<Payment> findByUserId(Long userId);
}
