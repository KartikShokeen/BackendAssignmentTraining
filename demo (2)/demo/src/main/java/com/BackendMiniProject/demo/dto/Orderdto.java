package com.BackendMiniProject.demo.dto;

import com.BackendMiniProject.demo.entity.OrderItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderdto {
    @NotNull(message = "product id should not be null")
    @Positive(message = "id should not be 0 or negative")
    private int id;
    @NotNull(message = "order details should not be null")
    @NotEmpty(message = "order details should not be empty")
    @Valid
    private List<OrderItems> orderDetails;

    private String orderType;
    private String totalQuantity;
    private String totalPrice;
    @JsonIgnore
    private int orderNo;
}
