package com.sdk.kheeti.service;


import com.sdk.kheeti.model.Order;
import com.sdk.kheeti.model.Product;
import com.sdk.kheeti.repositories.OrderRepository;
import com.sdk.kheeti.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepo productRepo;

    /**
     * Create a new order for the user.
     *
     * @param userId    The ID of the user.
     * @param productId The ID of the product being purchased.
     * @return The created order.
     */
    public Order createOrder(Long userId, Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());

        return orderRepo.save(order);
    }

    /**
     * Get all orders for a specific user.
     *
     * @param userId The user's ID.
     * @return A list of orders.
     */
    public List<Order> getUserOrders(Long userId) {
        return orderRepo.findByUserId(userId);
    }
}
