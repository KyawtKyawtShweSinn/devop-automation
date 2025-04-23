package com.lattmat.devop.mapper;

import com.lattmat.devop.dto.PaymentEventDto;
import com.lattmat.devop.entity.Payments;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PaymentEventDto convertPaymentDto(Payments payment) {
        return modelMapper.map(payment, PaymentEventDto.class);
    }

    public Payments convertPayment(PaymentEventDto paymentEventDto) {
        return modelMapper.map(paymentEventDto, Payments.class);
    }
}