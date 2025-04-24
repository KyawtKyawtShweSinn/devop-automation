package com.lattmat.devop.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderEventDto {
    private String orderId;
    private String customerName;
    private BigDecimal amount;
    private int quantityOrdered;
    private String productId;
}