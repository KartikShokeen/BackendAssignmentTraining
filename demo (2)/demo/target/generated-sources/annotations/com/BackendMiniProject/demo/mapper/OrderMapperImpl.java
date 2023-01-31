package com.BackendMiniProject.demo.mapper;

import com.BackendMiniProject.demo.dto.Orderdto;
import com.BackendMiniProject.demo.entity.OrderItems;
import com.BackendMiniProject.demo.entity.Orders;
import com.BackendMiniProject.demo.entity.Orders.OrdersBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-31T12:25:40+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Ubuntu)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Orderdto toOrderDto(Orders order) {
        if ( order == null ) {
            return null;
        }

        Orderdto orderdto = new Orderdto();

        orderdto.setId( order.getId() );
        List<OrderItems> list = order.getOrderDetails();
        if ( list != null ) {
            orderdto.setOrderDetails( new ArrayList<OrderItems>( list ) );
        }
        orderdto.setOrderType( order.getOrderType() );
        orderdto.setTotalQuantity( order.getTotalQuantity() );
        orderdto.setTotalPrice( order.getTotalPrice() );
        orderdto.setOrderNo( order.getOrderNo() );

        return orderdto;
    }

    @Override
    public List<Orderdto> toOrderDtoList(List<Orders> ordersList) {
        if ( ordersList == null ) {
            return null;
        }

        List<Orderdto> list = new ArrayList<Orderdto>( ordersList.size() );
        for ( Orders orders : ordersList ) {
            list.add( toOrderDto( orders ) );
        }

        return list;
    }

    @Override
    public Orders toOrder(Orderdto orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        OrdersBuilder orders = Orders.builder();

        orders.id( orderDTO.getId() );
        List<OrderItems> list = orderDTO.getOrderDetails();
        if ( list != null ) {
            orders.orderDetails( new ArrayList<OrderItems>( list ) );
        }
        orders.orderType( orderDTO.getOrderType() );
        orders.totalQuantity( orderDTO.getTotalQuantity() );
        orders.totalPrice( orderDTO.getTotalPrice() );
        orders.orderNo( orderDTO.getOrderNo() );

        return orders.build();
    }

    @Override
    public List<Orders> toOrderList(List<Orderdto> ordersDtoList) {
        if ( ordersDtoList == null ) {
            return null;
        }

        List<Orders> list = new ArrayList<Orders>( ordersDtoList.size() );
        for ( Orderdto orderdto : ordersDtoList ) {
            list.add( toOrder( orderdto ) );
        }

        return list;
    }
}
