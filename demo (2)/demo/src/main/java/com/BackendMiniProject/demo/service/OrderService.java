package com.BackendMiniProject.demo.service;

import com.BackendMiniProject.demo.entity.OrderItems;
import com.BackendMiniProject.demo.entity.Orders;
import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.Exceptions.InvalidOrderException;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.repository.OrderRepository;
import com.BackendMiniProject.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @Override
    public Orders getOrderById(int id) {
        Optional<Orders> order= orderRepository.findById(id);
        if(order.isPresent()){
            return  order.get();
        }
        else {
            throw new ResourceNotFoundException("Order does not exist with id "+ id);

        }
    }
    @Override
    public List<Orders> getAllOrders() {

            List<Orders> orders= orderRepository.findAll();
            if(orders.isEmpty()){
                throw new ResourceNotFoundException("No transactions to show");
            }
            else {
                return orders;
            }

    }
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Orders createPurchaseOrder(Orders order){
        increaseProductQuantity(order.getOrderDetails());
        order.setOrderType("Purchase order");
        order.setTotalPrice(calculateOrderPrice(order.getOrderDetails()));
        order.setTotalQuantity(calculateOrderQuantity(order.getOrderDetails()));
        return orderRepository.save(order);
    }
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Orders createSaleOrder(Orders order){
        if(validationsSaleOrder(order.getOrderDetails())){
            decreaseProductQuantity(order.getOrderDetails());
            order.setOrderType("Sale order");
            order.setTotalPrice(calculateOrderPrice(order.getOrderDetails()));
            order.setTotalQuantity(calculateOrderQuantity(order.getOrderDetails()));
        return orderRepository.save(order);
        }
        else {
            throw new InvalidOrderException("Please enter valid quantity to place order");
        }
    }

    private boolean validationsSaleOrder(List<OrderItems> orderDetails) {
        Boolean flag=true;
        for (OrderItems orderDetail: orderDetails){
            Optional<Product> currentProduct = productService.getProductById(orderDetail.getItemId());
            if(flag && orderDetail.getProductQuantity()>currentProduct.get().getProductQuantity()){
                flag= false;
            }
        }
        return flag;
    }
    private void increaseProductQuantity(List<OrderItems> orderDetails) {
        for (OrderItems orderDetail: orderDetails) {
            Optional<Product> currentProduct = productService.getProductById(orderDetail.getItemId());

            if (currentProduct.isPresent()){
               currentProduct.get().setProductQuantity(currentProduct.get().getProductQuantity()+orderDetail.getProductQuantity());
               productRepository.save(currentProduct.get());
            }
            else {
                throw new ResourceNotFoundException("Order cannot be placed as No product found with id"+currentProduct.get().getId());
            }
        }
    }

    private void decreaseProductQuantity(List<OrderItems> orderDetails) {
        for (OrderItems orderDetail: orderDetails){
            Optional<Product> currentProduct = productService.getProductById(orderDetail.getItemId());
            if (currentProduct.isPresent()){
                currentProduct.get().setProductQuantity(currentProduct.get().getProductQuantity()-orderDetail.getProductQuantity());
                productRepository.save(currentProduct.get());
            }
           else {
                throw new ResourceNotFoundException("Order cannot be placed as No product found with id"+currentProduct.get().getId());
            }
        }
    }

    private String calculateOrderPrice(List<OrderItems> orderDetails ){
        double finalPrice =0;
        for (OrderItems orderDetail: orderDetails){
            finalPrice =finalPrice + (orderDetail.getProductQuantity()*orderDetail.getProductPrice());
        }
        return String.valueOf(finalPrice);

    }
    private String calculateOrderQuantity(List<OrderItems> orderDetails) {


        double finalQuantity =0.0;
        for (OrderItems orderDetail: orderDetails){

            finalQuantity = finalQuantity + orderDetail.getProductQuantity();

        }
        return String.valueOf(finalQuantity);
    }




}
