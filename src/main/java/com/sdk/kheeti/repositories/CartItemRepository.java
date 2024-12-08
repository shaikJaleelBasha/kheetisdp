package com.sdk.kheeti.repositories;

import com.sdk.kheeti.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Find a cart item by userId and productId
    CartItem findByUserIdAndProductId(Long userId, Long productId);
    
    // Find all cart items for a specific user
    Iterable<CartItem> findByUserId(Long userId);
}
