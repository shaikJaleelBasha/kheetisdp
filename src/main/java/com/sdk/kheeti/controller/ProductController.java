package com.sdk.kheeti.controller;

import com.sdk.kheeti.model.Product;
import com.sdk.kheeti.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Fetch product details by ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(product);
    }
}
