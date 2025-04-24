package com.lattmat.devop.dto;

import lombok.Data;

@Data
public class PaymentEventDto {
    private String orderId;
    private boolean paymentSuccessful;
}