package com.lattmat.devop.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection="orders")
public class Orders {
    @Id
    private String orderId;
    private String customerName;
    private BigDecimal amount;
    private int quantityOrdered;
    private String productId;
}