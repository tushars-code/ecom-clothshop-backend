package com.example.backend.service;

import com.example.backend.model.Cart;
import com.example.backend.model.CartItem;
import com.example.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // ✅ Get cart for user
    public Cart getCartByUser(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setItems(new ArrayList<>());
            cartRepository.save(cart);
        }
        return cart;
    }

    // ✅ Add product to cart (use product data sent from frontend)
    public Cart addToCart(String userId, CartItem item) {
        Cart cart = getCartByUser(userId);

        // Debugging logs (optional)
        System.out.println("🛒 Adding to cart for user: " + userId);
        System.out.println("🧩 Product received: " + item.getTitle() + " (ID: " + item.getProductId() + ")");

        List<CartItem> items = cart.getItems();
        boolean exists = false;

        for (CartItem existing : items) {
            if (existing.getProductId().equals(item.getProductId())) {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                exists = true;
                break;
            }
        }

        if (!exists) {
            if (item.getQuantity() == 0) {
                item.setQuantity(1); // default quantity
            }
            items.add(item);
        }

        cartRepository.save(cart);
        return cart;
    }

    // ✅ Remove a specific item from cart
    public Cart removeItem(String userId, String productId) {
        Cart cart = getCartByUser(userId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        cartRepository.save(cart);
        return cart;
    }

    // ✅ Clear user cart
    public void clearCart(String userId) {
        Cart cart = getCartByUser(userId);
        cart.setItems(new ArrayList<>());
        cartRepository.save(cart);
    }
}
