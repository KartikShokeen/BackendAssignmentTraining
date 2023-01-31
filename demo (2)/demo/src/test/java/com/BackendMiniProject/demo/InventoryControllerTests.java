package com.BackendMiniProject.demo;

import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;



import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class InventoryControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void givenListOfProducts_whenShowInventory_thenReturnInventory() throws Exception{

        List<Product> productList= new ArrayList<>();
        productList.add(Product.builder()
                .id(1).productName("Milk Packet").productCategory("Dairy")
                .productQuantity(10.0).productDescription("Packet Of Milk").
                productCode("001").productPrice(100.0).build());

        productList.add(Product.builder()
                .id(2).productName("Salt Bag").productCategory("Masalas")
                .productQuantity(10.0).productDescription("Salt Bag").
                productCode("002").productPrice(100.0).build());

        given(inventoryService.getAllInventory()).willReturn(productList);

        ResultActions response = mockMvc.perform(get("/api/showInventory"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(productList.size())));

    }


}
