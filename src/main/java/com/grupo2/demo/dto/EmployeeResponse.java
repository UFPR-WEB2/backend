package com.grupo2.demo.dto;

import java.time.LocalDate;

public class EmployeeResponse {
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Boolean ativo;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long id, String nome, String email, LocalDate dataNascimento, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
