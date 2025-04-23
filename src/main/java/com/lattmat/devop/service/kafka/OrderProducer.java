package com.lattmat.devop.service.kafka;

import com.lattmat.devop.dto.OrderEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    @Autowired
    public OrderProducer(KafkaTemplate<String, OrderEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderEvents(OrderEventDto orderDto) {
        kafkaTemplate.send("order.created",orderDto.getProductId(), orderDto);
    }
}