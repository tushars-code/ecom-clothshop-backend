package com.example.backend.service;

import com.example.backend.model.Cart;
import com.example.backend.model.CartItem;
import com.example.backend.model.Order;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    // ✅ Place order from user’s cart
    public Order placeOrder(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            return null;
        }

        double total = cart.getItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(cart.getItems());
        order.setTotalAmount(total);
        order.setStatus("Processing");
        order.setOrderDate(new Date());

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId); // empty cart after placing order
        return savedOrder;
    }

    // ✅ Get all orders (for admin)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Get orders by user
    public List<Order> getOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }

    // ✅ Get single order by ID
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // ✅ Update order status
    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return null;
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
