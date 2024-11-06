package com.grupo2.demo.model;

import javax.xml.crypto.Data;

import jakarta.persistence.Entity;

@Entity
public class Employer extends User {

    private Data data_nascimento;

    public Employer(Long id, String nome, String email, boolean ativo, Data data_nascimento) {
        super(id, nome, email, ativo);
        this.data_nascimento = data_nascimento;
    }
    
    public Data getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Data data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

}
