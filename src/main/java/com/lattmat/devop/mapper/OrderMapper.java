package com.lattmat.devop.mapper;

import com.lattmat.devop.dto.OrderEventDto;
import com.lattmat.devop.entity.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderEventDto convertOrderDto(Orders order) {
        return modelMapper.map(order, OrderEventDto.class);
    }

    public Orders convertOrder(OrderEventDto orderDto) {
        return modelMapper.map(orderDto,Orders.class);
    }

    public List<OrderEventDto> convertOrderDtoList(List<Orders> order) {
        return order.stream().map(this::convertOrderDto).toList();
    }
}
