package com.tbd.health.controller;

import com.tbd.health.dto.OrderDto;
import com.tbd.health.entity.Order;
import com.tbd.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryPartner {

    @Autowired
    private OrderService orderService;
    @SqsListener("springboot-sqs")
    public ResponseEntity<OrderDto> getMessage(String message, OrderDto orderDto) {
        orderDto.setStatus("order_received");
        updateOrderStatus(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }
    @PutMapping("/updateStatus")
    public ResponseEntity<OrderDto> updateOrderStatus(@RequestBody OrderDto orderDto){
        orderService.updateOrderStatus(orderDto.getId(), orderDto.getStatus());
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }
}
