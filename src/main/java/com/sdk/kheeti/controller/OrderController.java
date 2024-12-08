package com.sdk.kheeti.controller;

import com.sdk.kheeti.dto.OrderRequest;
import com.sdk.kheeti.model.Order;
import com.sdk.kheeti.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sdk.kheeti.service.OrderService;





@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Endpoint to create a new order.
     *
     * @param order The order details sent by the frontend.
     * @return The created order.
     */
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody Order order) {
        try {
            if (order.getUserId() == null || order.getProductId() == null) {
                return ResponseEntity.badRequest().body("User ID and Product ID are required.");
            }

            Order newOrder = orderService.createOrder(order.getUserId(), order.getProductId());
            return ResponseEntity.ok(newOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to create order: " + e.getMessage());
        }
    }

    /**
     * Endpoint to fetch all orders for a user.
     *
     * @param userId The ID of the user.
     * @return A list of orders.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        try {
            List<Order> orders = orderService.getUserOrders(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
