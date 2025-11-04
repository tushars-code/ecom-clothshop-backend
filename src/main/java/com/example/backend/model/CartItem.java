package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private String productId;   // should match frontend product.id
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private double rating;
    private int quantity;
    private int stock;
}
