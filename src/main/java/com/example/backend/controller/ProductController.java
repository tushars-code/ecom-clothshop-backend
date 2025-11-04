package com.example.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "https://ecom-clothshop-frontend.vercel.app/")
public class ProductController {

    private static final String FAKE_API = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ Get all products
    @GetMapping
    public List<Map<String, Object>> getAllProducts() {
        Map<String, Object>[] products = restTemplate.getForObject(FAKE_API, Map[].class);
        List<Map<String, Object>> refinedList = new ArrayList<>();
        Random random = new Random();

        for (Map<String, Object> p : products) {
            Map<String, Object> product = new LinkedHashMap<>();
            product.put("id", p.get("id"));
            product.put("title", p.get("title"));
            product.put("description", p.get("description"));
            product.put("price", p.get("price"));
            product.put("category", p.get("category"));
            product.put("image", p.get("image"));
            product.put("rating", p.get("rating")); // ✅ include rating object
            product.put("stock", random.nextInt(50) + 1); // random stock 1–50
            refinedList.add(product);
        }

        return refinedList;
    }

    // ✅ Get single product by ID
    @GetMapping("/{id}")
    public Map<String, Object> getProductById(@PathVariable Long id) {
        Map<String, Object> p = restTemplate.getForObject(FAKE_API + "/" + id, Map.class);
        if (p == null) return null;

        Map<String, Object> product = new LinkedHashMap<>();
        product.put("id", p.get("id"));
        product.put("title", p.get("title"));
        product.put("description", p.get("description"));
        product.put("price", p.get("price"));
        product.put("category", p.get("category"));
        product.put("image", p.get("image"));
        product.put("rating", p.get("rating")); // ✅ include nested rating
        product.put("stock", new Random().nextInt(50) + 1);
        return product;
    }
}
