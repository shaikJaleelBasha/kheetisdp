package com.sdk.kheeti.controller;

import com.sdk.kheeti.model.CartItem;
import com.sdk.kheeti.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem) {
        cartService.addToCart(cartItem.getUserId(), cartItem.getProduct().getId(), cartItem.getQuantity());
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("/remove")
public ResponseEntity<String> removeFromCart(@RequestBody Map<String, Object> cartRequest) {
    Long userId = ((Number) cartRequest.get("userId")).longValue();
    Long productId = ((Number) cartRequest.get("productId")).longValue();

    if (userId == null || productId == null) {
        return ResponseEntity.badRequest().body("Invalid request payload. Provide userId and productId.");
    }

    boolean isRemoved = cartService.removeFromCart(userId, productId);
    if (isRemoved) {
        return ResponseEntity.ok("Product removed from cart.");
    } else {
        return ResponseEntity.status(404).body("Cart item not found.");
    }
}


    @PostMapping("/buy-all")
    public ResponseEntity<String> buyAll(@RequestBody Long userId) {
        cartService.buyAll(userId);
        return ResponseEntity.ok("All items purchased successfully!");
    }



   

}
