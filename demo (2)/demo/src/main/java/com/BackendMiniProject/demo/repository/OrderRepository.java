package com.BackendMiniProject.demo.repository;

import com.BackendMiniProject.demo.entity.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Orders,String> {


    Optional<Orders> findById(int id);
}
