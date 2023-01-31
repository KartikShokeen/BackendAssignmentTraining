package com.BackendMiniProject.demo.service;


import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InventoryServiceTests {
    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private ProductRepository productRepositoryMock;

    @Test
    public void findInventory(){
        Product product = givenProduct();
        List<Product> products = Collections.singletonList(product);
        when(productRepositoryMock.findAll()).thenReturn(products);
        assertEquals(product,inventoryService.getAllInventory().get(0));
    }

    private Product givenProduct() {
        Product product =Product.builder()
                .id(1)
                .productName("Milk Packet")
                .productCategory("Dairy")
                .productQuantity(10.0).
                productDescription("Packet Of Milk")
                .productCode("001")
                .productPrice(100.0)
                .build();
        return product;
    }
}
