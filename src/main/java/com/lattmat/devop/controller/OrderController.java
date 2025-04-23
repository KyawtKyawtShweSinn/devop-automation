package com.lattmat.devop.controller;

import com.lattmat.devop.dto.OrderEventDto;
import com.lattmat.devop.service.OrderService;
import com.lattmat.devop.service.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderProducer orderProducer;

    @Autowired
    public OrderController(OrderService orderService, OrderProducer orderProducer) {
        this.orderService = orderService;
        this.orderProducer = orderProducer;
    }

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody OrderEventDto orderDto) {
        orderService.createOrder(orderDto);
        orderProducer.publishOrderEvents(orderDto);
        return new ResponseEntity<>("Order created successfully.", HttpStatus.CREATED);
    }
}
