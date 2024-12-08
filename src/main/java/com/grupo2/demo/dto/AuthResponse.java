package com.grupo2.demo.dto;

import com.grupo2.demo.model.User.User;

public class AuthResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser(Object user) {
        User object = (User) user;
        this.name = object.getNome();
        this.email = object.getEmail();
        this.active = object.isAtivo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmai(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
