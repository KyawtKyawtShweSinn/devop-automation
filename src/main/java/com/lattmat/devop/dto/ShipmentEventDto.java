package com.lattmat.devop.dto;

import lombok.Data;

@Data
public class ShipmentEventDto {
    private String orderId;
    private String trackingNumber;
}