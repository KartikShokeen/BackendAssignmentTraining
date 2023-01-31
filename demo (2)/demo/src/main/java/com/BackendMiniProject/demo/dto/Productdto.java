package com.BackendMiniProject.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productdto {


    @NotNull(message = "product id should not be null")
    @Positive(message = "id cannot be zero or negative")
    private int id;
    @NotNull(message = "product name should not be null")
    @NotBlank(message = "product name should not be blank")
    @Size(min = 4,max = 15,message = "product name should between 4-15 characters")
    private String productName;
    @NotNull(message = "product Description should not be null")
    @NotBlank(message = "product Description should not be blank")
    private String productDescription;
    @NotNull(message = "product name should not be null")
    @NotBlank(message = "product name should not be blank")
    private String productCategory;
    @NotNull(message = "product Price should not be null")
    @Positive(message = "product price cannot be zero or negative")
    private Double productPrice;

    private Double productQuantity;
    @NotNull(message = "product Code should not be null")
    @NotBlank(message = "product Code should not be blank")
    private String productCode;

}
