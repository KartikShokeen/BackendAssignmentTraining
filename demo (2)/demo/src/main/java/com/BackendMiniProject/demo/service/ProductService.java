package com.BackendMiniProject.demo.service;


import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.Exceptions.ResourceAlreadyExistsException;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {

        List<Product> products=productRepository.findAll();
        if(products.isEmpty()){
            throw new ResourceNotFoundException("No Products found");
        }
        else{
            return products;
        }

    }
    @Override
    public Optional<Product> getProductById(int id){

            Optional<Product> product= productRepository.findById(id);
            if(product.isEmpty()) {
                throw new ResourceNotFoundException("Product not found with id: " + id);
            }
            else{
                return product;
            }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Product addProduct(Product product){
        if(productRepository.findById(product.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException("Product already present cannot be added again");
        }
        else{
            product.setProductQuantity(0.0);
            return productRepository.save(product);
        }

    }
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Product> addMultipleProduct(List<Product> products){
        List<Product> result = new ArrayList<>();
        for (Product product:products){
            if (productRepository.findById(product.getId()).isPresent()) {
                continue;
            }
            product.setProductQuantity(0.0);
            result.add(productRepository.save(product));
        }
        return result;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Product updateProduct(int id,Product productDetails){
        if (productRepository.findById(productDetails.getId()).isPresent()) {
            return productRepository.save(productDetails);
        }
        else {
            throw new ResourceNotFoundException("Product not found with id :" +id);

        }
    }
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Product deleteProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return product.get();
        }
        else {
            throw new ResourceNotFoundException("Product not found with id :" +id);
        }


    }

}
