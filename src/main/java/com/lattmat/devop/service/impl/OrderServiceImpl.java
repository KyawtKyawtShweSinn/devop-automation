package com.lattmat.devop.service.impl;

import com.lattmat.devop.dto.OrderEventDto;
import com.lattmat.devop.entity.Orders;
import com.lattmat.devop.repository.OrderRepository;
import com.lattmat.devop.service.OrderService;
import com.lattmat.devop.utility.GenricMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final GenricMapperUtility genricMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GenricMapperUtility genricMapper) {
        this.orderRepository = orderRepository;
        this.genricMapper = genricMapper;
    }

    @Override
    public void createOrder(OrderEventDto orderDto) {
        Orders order = genricMapper.mapToEntity(orderDto, Orders.class);
        orderRepository.save(order);
    }
}