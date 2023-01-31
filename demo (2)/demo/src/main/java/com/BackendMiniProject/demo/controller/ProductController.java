package com.BackendMiniProject.demo.controller;

import com.BackendMiniProject.demo.dto.Productdto;
import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.Exceptions.ResourceAlreadyExistsException;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.mapper.ProductMapper;
import com.BackendMiniProject.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {


    private final ProductMapper productMapper;

    @Autowired
    ProductService productService;

    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/findAllProducts")
    public ResponseEntity<?> getAllProducts() {
        ResponseEntity<?> result;
        try{
            List<Productdto> productdtoList = productMapper.toProductDtoList(productService.getAllProducts());
            return result = ResponseEntity.ok(productdtoList);

        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/findProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        ResponseEntity<?> result;
        try{
            //convert entity to DTO
            Productdto productdto = productMapper.toProductDto(productService.getProductById(id).get());
            return result = ResponseEntity.ok(productdto);
        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?>  saveProduct(@Valid @RequestBody Productdto productDTO){
        ResponseEntity<?> result;
        try{
            //Convert DTO to entity
            Product product = productMapper.toProduct(productDTO);
            //convert entity to DTO
            Productdto productdto = productMapper.toProductDto(productService.addProduct(product));
            return result = ResponseEntity.ok(productdto);
        }catch (ResourceAlreadyExistsException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/addMultipleProducts")
    public ResponseEntity<?> saveMultipleProduct(@Valid @RequestBody List<Productdto> productsDTO){
        ResponseEntity<?> result;
        try{
            //Convert DTO to entity
            List<Product> productList = productMapper.toProductList(productsDTO);
            //convert entity to DTO
            List<Productdto> savedProductsDTO = productMapper.toProductDtoList(productService.addMultipleProduct(productList));
            return result = ResponseEntity.ok(savedProductsDTO);
        }catch (ResourceAlreadyExistsException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable("id") int id, @RequestBody Productdto productDetailsDTO) {
        ResponseEntity<?> result;
        try{
            //Convert DTO to entity
            Product product = productMapper.toProduct(productDetailsDTO);
            //convert entity to DTO
            Productdto productdto = productMapper.toProductDto(productService.updateProduct(id,product));
            return result = ResponseEntity.ok(productdto);
        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //add response check if successful or not
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        ResponseEntity<?> result;
        try {
            productService.deleteProductById(id);
            return result = ResponseEntity.ok("Product Successfully Deleted with id " + id);
        }
        catch(ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
