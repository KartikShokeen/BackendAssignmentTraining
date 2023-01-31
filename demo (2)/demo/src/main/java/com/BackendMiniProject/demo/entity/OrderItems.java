package com.BackendMiniProject.demo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Getter
@Builder
@NotNull(message = "value should not be null")
public class OrderItems {


    @Positive(message = "item cannot be zero or negative")
    private int itemId;

    @NotBlank(message = "product name should not be blank")
    @Size(min = 4,max = 15,message = "product name should between 4-15 characters")
    private String productName;

    @NotBlank(message = "product Description should not be blank")
    private String productDescription;

    @NotBlank(message = "product name should not be blank")
    private String productCategory;

    @Positive(message = "product price cannot be zero or negative")
    private Double productPrice;

    @Positive(message = "product price cannot be zero or negative")
    private Double productQuantity;

    @NotBlank(message = "product Code should not be blank")
    @Indexed(unique = true)
    private String productCode;


}
