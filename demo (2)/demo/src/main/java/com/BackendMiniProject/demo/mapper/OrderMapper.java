package com.BackendMiniProject.demo.mapper;


import com.BackendMiniProject.demo.dto.Orderdto;
import com.BackendMiniProject.demo.entity.Orders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface OrderMapper {
    Orderdto toOrderDto(Orders order);
    List<Orderdto> toOrderDtoList(List<Orders> ordersList);
    Orders toOrder(Orderdto orderDTO);
    List<Orders> toOrderList(List<Orderdto> ordersDtoList);
}
