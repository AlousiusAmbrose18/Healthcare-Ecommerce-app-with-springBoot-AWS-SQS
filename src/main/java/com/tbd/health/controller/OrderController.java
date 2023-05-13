package com.tbd.health.controller;
import com.tbd.health.dto.OrderDto;
import com.tbd.health.entity.Order;
import com.tbd.health.service.OrderService;
import com.tbd.health.util.OrderParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndPoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        queueMessagingTemplate.convertAndSend(sqsEndPoint, orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }
}

