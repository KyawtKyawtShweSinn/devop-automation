package com.lattmat.devop.dto;

import lombok.Data;

@Data
public class InventoryEventDto {
    private String productId;
    private int quantity;
    private String location;
    private String orderId;
}
