package com.BackendMiniProject.demo.repository;

import com.BackendMiniProject.demo.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    Optional<Product> findById(int id);
}
