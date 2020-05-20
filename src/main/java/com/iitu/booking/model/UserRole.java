package com.iitu.booking.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity{

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "code")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
