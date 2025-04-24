package com.lattmat.devop.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="shipments")
public class Shipments {
    @Id
    private String orderId;
    private String trackingNumber;
}