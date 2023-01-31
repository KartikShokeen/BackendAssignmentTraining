package com.BackendMiniProject.demo.controller;

import com.BackendMiniProject.demo.dto.Orderdto;
import com.BackendMiniProject.demo.entity.Orders;
import com.BackendMiniProject.demo.Exceptions.InvalidOrderException;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.mapper.OrderMapper;
import com.BackendMiniProject.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class OrderController {


    private final OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

    public OrderController(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders(){
        ResponseEntity<?> result;
        try{
            List<Orderdto> ordersList = orderMapper.toOrderDtoList(orderService.getAllOrders());
            return result = ResponseEntity.ok(ordersList);
        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<?>  getOrderById(@Valid @PathVariable int id){
        ResponseEntity<?> result;
        try{
            //convert entity to DTO
            Orderdto orderDTO = orderMapper.toOrderDto(orderService.getOrderById(id));
            return result = ResponseEntity.ok(orderDTO);
        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/addSaleOrder")
    public ResponseEntity<?> createSaleOrder(@Valid@RequestBody Orderdto orderDTO){
        ResponseEntity<?> result;
        try{
            //convert Dto to entity
            Orders savedOrder = orderService.createSaleOrder(orderMapper.toOrder(orderDTO));
            //convert entity to DTO
            Orderdto savedOrderdto = orderMapper.toOrderDto(savedOrder);
            return result = ResponseEntity.ok(savedOrderdto);
        }catch (InvalidOrderException e){
            return result = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
    @PostMapping("/addPurchaseOrder")
    public ResponseEntity<?> createPurchaseOrder(@Valid@RequestBody Orderdto orderDTO){
        ResponseEntity<?> result;
        try{
            //convert Dto to entity
            Orders savedOrder = orderService.createPurchaseOrder(orderMapper.toOrder(orderDTO));
            //convert entity to DTO
            Orderdto savedOrderdto = orderMapper.toOrderDto(savedOrder);
            return result = ResponseEntity.ok(savedOrderdto);
        }catch (InvalidOrderException e){
            return result = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
        catch (ResourceNotFoundException e){
            return result = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

}
