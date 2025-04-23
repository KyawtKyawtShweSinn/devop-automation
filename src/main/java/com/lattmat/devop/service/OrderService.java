package com.lattmat.devop.service;

import com.lattmat.devop.dto.OrderEventDto;

public interface OrderService {
    void createOrder(OrderEventDto orderDto);
}
