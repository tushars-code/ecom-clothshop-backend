package com.example.backend.controller;

import com.example.backend.model.Cart;
import com.example.backend.model.CartItem;
import com.example.backend.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "https://ecom-clothshop-frontend.vercel.app/") // frontend URL
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Get user's cart
    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return cartService.getCartByUser(userId);
    }

    // Add a product to cart
    @PostMapping("/{userId}")
    public Cart addToCart(@PathVariable String userId, @RequestBody CartItem item) {
        System.out.println("🧾 Request received to add item to cart for user: " + userId);
        return cartService.addToCart(userId, item);
    }

    // Clear entire cart
    @DeleteMapping("/{userId}")
    public void clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
    }

    // Remove one specific product
    @DeleteMapping("/{userId}/{productId}")
    public Cart removeItem(@PathVariable String userId, @PathVariable String productId) {
        return cartService.removeItem(userId, productId);
    }
}
