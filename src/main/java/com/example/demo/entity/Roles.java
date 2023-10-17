package com.example.demo.entity;

import lombok.Getter;

@Getter

public enum Roles {
    ROLE_ADMIN,ROLE_USER;

    String role;

    public String getRole() {
        return role;
    }

}
