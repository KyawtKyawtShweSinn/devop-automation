package com.lattmat.devop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    @JsonProperty("userName")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("userRole")
    private String role;
    private boolean isActive;
    private boolean isLock;
    private boolean isDeleted;
    private Date joinDate;
}