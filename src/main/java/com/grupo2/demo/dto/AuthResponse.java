package com.grupo2.demo.dto;

import com.grupo2.demo.model.User.User;

public class AuthResponse {
    private String name;
    private String emai;
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
        this.emai = object.getEmail();
        this.active = object.isAtivo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
