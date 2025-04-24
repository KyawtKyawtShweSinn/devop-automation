package com.lattmat.devop.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="payments")
public class Payments {
    @Id
    private String orderId;
    private boolean successful;
}