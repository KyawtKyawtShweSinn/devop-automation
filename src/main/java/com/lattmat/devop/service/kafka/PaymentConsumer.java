package com.lattmat.devop.service.kafka;

import com.lattmat.devop.dto.InventoryEventDto;
import com.lattmat.devop.dto.PaymentEventDto;
import com.lattmat.devop.entity.Payments;
import com.lattmat.devop.repository.PaymentRepository;
import com.lattmat.devop.utility.GenricMapperUtility;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, PaymentEventDto> kafkaTemplate;
    private final GenricMapperUtility genricMapper;

    public PaymentConsumer(PaymentRepository paymentRepository,
                           KafkaTemplate<String, PaymentEventDto> kafkaTemplate, GenricMapperUtility genricMapper) {
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.genricMapper = genricMapper;
    }

    @KafkaListener(topics = "inventory.checked")
    public void onInventoryChecked(InventoryEventDto inventoryEventDto) {
        PaymentEventDto paymentEventDto = new PaymentEventDto();
        paymentEventDto.setOrderId(inventoryEventDto.getOrderId());
        paymentEventDto.setPaymentSuccessful(true);

        Payments payments = genricMapper.mapToEntity(paymentEventDto, Payments.class );
        paymentRepository.save(payments);
        kafkaTemplate.send("payment.processed", paymentEventDto.getOrderId(), paymentEventDto);
    }
}