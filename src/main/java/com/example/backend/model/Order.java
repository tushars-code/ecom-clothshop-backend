package com.example.backend.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<CartItem> items;
    private double totalAmount;
    private String status; // e.g. "Processing", "Shipped", "Delivered"
    private Date orderDate = new Date();
}
