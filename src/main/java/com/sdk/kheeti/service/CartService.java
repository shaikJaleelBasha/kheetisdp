package com.sdk.kheeti.service;

import com.sdk.kheeti.model.CartItem;
import com.sdk.kheeti.repositories.CartRepository;
import com.sdk.kheeti.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<CartItem> getCartItems(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public void addToCart(Long userId, Long productId, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProduct(productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found")));
        cartItem.setQuantity(quantity);
        cartRepo.save(cartItem);
    }



    public void buyAll(Long userId) {
        List<CartItem> cartItems = cartRepo.findByUserId(userId);
        // Logic to handle purchases
        cartRepo.deleteAll(cartItems); // Clear the cart
    }
    public boolean removeFromCart(Long userId, Long productId) {
        CartItem cartItem = cartRepo.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartRepo.delete(cartItem);
            return true;
        }
        return false; // Item not found
    }
    
    
}
