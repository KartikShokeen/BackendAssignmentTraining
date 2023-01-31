package com.BackendMiniProject.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("orders")
public class Orders {

    @Id
    private int id;
    private List<OrderItems> orderDetails;
    private String orderType;
    private String totalQuantity;
    private String totalPrice;
    @JsonIgnore
    @Indexed(unique = true)
    private int orderNo;


}
