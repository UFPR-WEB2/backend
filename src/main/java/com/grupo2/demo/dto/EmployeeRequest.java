package com.grupo2.demo.dto;

import java.time.LocalDate;

import com.grupo2.demo.model.User.Employee;

public class EmployeeRequest {
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String nome, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public Employee toEmployee() {
        Employee user = new Employee();
        user.setNome(this.nome);
        user.setEmail(this.email);
        user.setPassword(this.senha);
        user.setDataNascimento(this.dataNascimento);
        return user;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
}
