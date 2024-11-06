package com.grupo2.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends User {

    public Client(Long id, String nome, String email, boolean ativo) {
        super(id, nome, email, ativo);
    }

}
