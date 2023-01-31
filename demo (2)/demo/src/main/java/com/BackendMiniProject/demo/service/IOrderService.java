package com.BackendMiniProject.demo.service;

import com.BackendMiniProject.demo.entity.Orders;

import java.util.List;

public interface IOrderService {

    public Orders getOrderById(int id);
    public List<Orders> getAllOrders();
    public Orders createSaleOrder(Orders orderItem);
    public Orders createPurchaseOrder(Orders orderItem);
}
