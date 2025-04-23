package com.lattmat.devop.service.impl;

import com.lattmat.devop.dto.OrderEventDto;
import com.lattmat.devop.entity.Orders;
import com.lattmat.devop.mapper.OrderMapper;
import com.lattmat.devop.repository.OrderRepository;
import com.lattmat.devop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void createOrder(OrderEventDto orderDto) {
        Orders order = orderMapper.convertOrder(orderDto);
        orderRepository.save(order);
    }
}