package com.sdk.kheeti.repositories;

import com.sdk.kheeti.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    @Query("SELECT c FROM CartItem c WHERE c.userId = :userId AND c.product.id = :productId")
    CartItem findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);


    
    
}
