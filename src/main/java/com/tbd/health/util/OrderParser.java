package com.tbd.health.util;

import com.tbd.health.dto.OrderDto;
import com.tbd.health.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderParser {

    public OrderDto orderToOrderDto(Order order){
        return new OrderDto(order.getId(), order.getCustomer(), order.getProduct(), order.getQuantity(), order.getStatus());
    }

    public Order orderDtoToOrder(OrderDto orderDto){
        return new Order(orderDto.getId(), orderDto.getCustomer(), orderDto.getProduct(), orderDto.getQuantity(), orderDto.getStatus());
    }
}
