package com.lattmat.devop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    @JsonProperty("userName")
    private String name;

    @JsonProperty("password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
