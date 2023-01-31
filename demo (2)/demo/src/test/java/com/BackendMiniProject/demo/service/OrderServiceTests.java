package com.BackendMiniProject.demo.service;

import com.BackendMiniProject.demo.entity.OrderItems;
import com.BackendMiniProject.demo.entity.Orders;
import com.BackendMiniProject.demo.Exceptions.InvalidOrderException;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTests {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepositoryMock;

    private static final int id=1;

    @Test
    public void getAllOrdersTest(){
        Orders order = givenOrders();
        List<Orders> ordersList = Collections.singletonList(order);
        when(orderRepositoryMock.findAll()).thenReturn(ordersList);
        assertEquals(order,orderService.getAllOrders().get(0));
    }


    //positive case
    @Test
    public void findOrdersById(){
        Orders order = givenOrders();
        when(orderRepositoryMock.findById(id)).thenReturn(Optional.of(order));
        assertEquals(order,orderService.getOrderById(id));
    }
    //negative post case
    @Test
    public void findProductByIdNullCase(){
        Orders order = givenOrders();
        when(orderRepositoryMock.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->{
            orderService.getOrderById(id);
        },"Order does not exist with id "+ id);

    }

    //positive case
    @Test
    public void createProduct(){
        Orders order = givenOrders();
        System.out.println(order);
        when(orderRepositoryMock.save(order)).thenReturn(order);
        assertEquals(order,orderService.createSaleOrder(order));
    }
    //negative post case
    @Test
    public void createProductNullCase(){
        Orders order = givenOrders();

        when(orderRepositoryMock.save(order)).thenReturn(order);
        assertThrows(InvalidOrderException.class,()->{
            orderService.createSaleOrder(order);
        },"Please enter valid quantity to place order"+ id);

    }

    private Orders givenOrders() {
        Orders orders = Orders.builder()
                .id(1)
                .orderDetails(setOrderDetails())
                .orderType("Sale Order")
                .totalQuantity("1")
                .totalPrice("100")
                .orderNo(001)
                .build();
        return orders;
    }

    private List<OrderItems> setOrderDetails() {
        List<OrderItems> orderItemsList = new ArrayList<>();
        orderItemsList.add(OrderItems.builder()
                .itemId(1)
                .productName("Milk Packet")
                .productDescription("Packet Of Milk")
                .productCategory("Dairy")
                .productPrice(100.0)
                .productQuantity(10.0)
                .productCode("001")
                .build());
        orderItemsList.add(OrderItems.builder()
                .itemId(2)
                .productName("Salt Bag")
                .productDescription("Packet Of Salt")
                .productCategory("Spices")
                .productPrice(100.0)
                .productQuantity(10.0)
                .productCode("002")
                .build());
        return orderItemsList;
    }

}
