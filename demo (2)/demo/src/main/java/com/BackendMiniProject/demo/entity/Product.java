package com.BackendMiniProject.demo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "products")
public class Product {

    @Id
    private int id;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Double productPrice;
    private Double productQuantity;
    @Indexed(unique = true)
    private String productCode;


}
