package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products") // MongoDB collection name
public class Product {

    @Id
    private String id;

    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    private double rating; // store rating.rate
    private int ratingCount; // store rating.count
    private int stock; // random or managed value
}
