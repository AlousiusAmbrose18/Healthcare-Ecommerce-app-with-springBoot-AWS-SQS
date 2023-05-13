package com.tbd.health.service;
import com.tbd.health.dto.OrderDto;
import com.tbd.health.entity.Order;
import com.tbd.health.repository.OrderRepository;
import com.tbd.health.util.OrderParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderParser parser;

    public void createOrder(OrderDto orderDto) {
        Order order = parser.orderDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    public void updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

}

