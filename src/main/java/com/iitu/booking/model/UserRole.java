package com.iitu.booking.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    CUSTOMER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
