package com.example.backend.repository;

import com.example.backend.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByUserId(String userId);
}
