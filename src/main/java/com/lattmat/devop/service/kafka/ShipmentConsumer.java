package com.lattmat.devop.service.kafka;

import com.lattmat.devop.dto.PaymentEventDto;
import com.lattmat.devop.dto.ShipmentEventDto;
import com.lattmat.devop.entity.Shipments;
import com.lattmat.devop.repository.ShipmentRepository;
import com.lattmat.devop.utility.GenricMapperUtility;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShipmentConsumer {
    private final ShipmentRepository shipmentRepository;
    private final KafkaTemplate<String, ShipmentEventDto> kafkaTemplate;
    private final GenricMapperUtility genricMapper;

    public ShipmentConsumer (ShipmentRepository shipmentRepository, KafkaTemplate<String, ShipmentEventDto> kafkaTemplate, GenricMapperUtility genricMapper) {
        this.shipmentRepository = shipmentRepository;
        this.genricMapper = genricMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payment.processed")
    public void onPaymentProcessed(PaymentEventDto paymentEventDto) {
        if (!paymentEventDto.isPaymentSuccessful()) return;
        // create shipment
        ShipmentEventDto shipmentEventDto = new ShipmentEventDto();
        shipmentEventDto.setOrderId(paymentEventDto.getOrderId());
        shipmentEventDto.setTrackingNumber(UUID.randomUUID().toString());
        // persist
        Shipments shipment = genricMapper.mapToEntity(paymentEventDto, Shipments.class);
        shipmentRepository.save(shipment);
        // publish if needed
        kafkaTemplate.send("shipment.created", shipmentEventDto.getOrderId(), shipmentEventDto);
    }
}