package com.sdk.kheeti.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdk.kheeti.controller.Payment;
import com.sdk.kheeti.model.Product;
import com.sdk.kheeti.repositories.PaymentRepository;
import com.sdk.kheeti.repositories.ProductRepo;
@Service
public class PaymentService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    /**
     * Process payment and handle any validation.
     */
    public Payment processPayment(Long userId, Long productId, String paymentMethod) {
        // Fetch the product to verify it exists
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create a new Payment object
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setProductId(productId);
        payment.setAmount(product.getPrice());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("Completed");
        payment.setPaymentDate(new Date());

        // Save the payment record
        return paymentRepo.save(payment);
    }

    public List<Payment> getUserPayments(Long userId) {
        return paymentRepo.findByUserId(userId);
    }
}
