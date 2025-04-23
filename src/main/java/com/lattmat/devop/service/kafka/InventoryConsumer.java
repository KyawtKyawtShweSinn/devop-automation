package com.lattmat.devop.service.kafka;

import com.lattmat.devop.dto.InventoryEventDto;
import com.lattmat.devop.dto.OrderEventDto;
import com.lattmat.devop.entity.Inventories;
import com.lattmat.devop.repository.InventoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InventoryConsumer {
    private final InventoryRepository inventoryRepository;
    private final KafkaTemplate<String, InventoryEventDto> kafkaTemplate;

    public InventoryConsumer(InventoryRepository inventoryRepository, KafkaTemplate<String, InventoryEventDto> kafkaTemplate) {
        this.inventoryRepository = inventoryRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order.created")
    public void onOrderCreated(OrderEventDto orderDto) {
        Inventories inventory = inventoryRepository.findById(orderDto.getProductId()).orElseThrow(() -> new NoSuchElementException("Id not found."));

        if (inventory.getQuantity() >= orderDto.getQuantityOrdered()) {
            // subtract stock
            inventory.setQuantity(inventory.getQuantity() - orderDto.getQuantityOrdered());
            inventoryRepository.save(inventory);
            // publish inventory event
            InventoryEventDto inventoryEventDto = new InventoryEventDto();
            inventoryEventDto.setOrderId(orderDto.getOrderId());
            kafkaTemplate.send("inventory.checked", inventoryEventDto.getOrderId(), inventoryEventDto);
        } else {
            // insufficient stock: skip next steps or handle alert
            System.err.println("Insufficient stock for " + orderDto.getProductId());
        }
    }
}