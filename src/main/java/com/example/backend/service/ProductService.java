package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final String FAKE_API = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ Fetch and save products from FakeStore API
    public List<Product> fetchAndSaveProducts() {
        Product[] products = restTemplate.getForObject(FAKE_API, Product[].class);
        if (products != null) {
            productRepository.deleteAll();
            productRepository.saveAll(Arrays.asList(products));
        }
        return productRepository.findAll();
    }

    // ✅ Get all products
    public List<Product> getAllProducts() {
        List<Product> existing = productRepository.findAll();
        if (existing.isEmpty()) {
            return fetchAndSaveProducts();
        }
        return existing;
    }

    // ✅ Get product by ID
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }
}
