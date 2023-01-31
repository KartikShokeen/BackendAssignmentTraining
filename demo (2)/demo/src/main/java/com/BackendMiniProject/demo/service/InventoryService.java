package com.BackendMiniProject.demo.service;

import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllInventory() {

        List<Product> inventory = productRepository.findAll();
        if (inventory.isEmpty()) {
            throw new ResourceNotFoundException("Inventory is Empty");
        } else {
            return inventory;
        }
    }
}
