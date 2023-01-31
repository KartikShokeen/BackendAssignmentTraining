package com.BackendMiniProject.demo.service;

import com.BackendMiniProject.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public List<Product> getAllProducts();
    public Optional<Product> getProductById(int id);
    public Product addProduct(Product product);
    public List<Product> addMultipleProduct(List<Product> products);
    public Product updateProduct(int id,Product productDetails);
    public Product deleteProductById(int id);
}
