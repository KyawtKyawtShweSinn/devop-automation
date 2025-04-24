package com.lattmat.devop.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection="users")
public class Users {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String password;
    private String role;
    private boolean isActive;
    private boolean isLock;
    private boolean isDeleted;
    private Date joinDate;
}
