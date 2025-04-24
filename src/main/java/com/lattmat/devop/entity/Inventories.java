package com.lattmat.devop.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="inventories")
public class Inventories {
    @Id
    private String id;
    private int quantity;
    private String location;
}