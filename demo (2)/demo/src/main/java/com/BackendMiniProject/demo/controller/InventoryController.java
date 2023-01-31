package com.BackendMiniProject.demo.controller;

import com.BackendMiniProject.demo.dto.Productdto;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.mapper.ProductMapper;
import com.BackendMiniProject.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/api")
public class InventoryController {



    private final ProductMapper productMapper;
    @Autowired
    InventoryService inventoryService;

    public InventoryController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    @GetMapping("/showInventory")
    public ResponseEntity<?>showInventory(){
        ResponseEntity<?> result;
        try{
            List<Product> productList = inventoryService.getAllInventory();

            List<Productdto> productdtoList = productMapper.toProductDtoList(productList);

            return result = ResponseEntity.ok(productdtoList);

        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
