package com.sdk.kheeti.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdk.kheeti.model.Product;
import com.sdk.kheeti.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Map<String, Object> payload) {
        try {
            // Validate request payload
            if (!payload.containsKey("userId") || !payload.containsKey("productId") || !payload.containsKey("paymentMethod")) {
                return ResponseEntity.badRequest().body("Missing required fields: userId, productId, or paymentMethod");
            }

            Long userId = Long.parseLong(payload.get("userId").toString());
            Long productId = Long.parseLong(payload.get("productId").toString());
            String paymentMethod = payload.get("paymentMethod").toString();

            // Process payment
            Payment payment = paymentService.processPayment(userId, productId, paymentMethod);

            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            // Log error and return appropriate response
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Payment>> getUserPayments(@PathVariable Long userId) {
        try {
            List<Payment> payments = paymentService.getUserPayments(userId);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }
}
