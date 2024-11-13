package com.grupo2.demo.dto;

import com.grupo2.demo.model.User.Customer;

public class CustomerRequest {
    private String nome;
    private String email;
    private String password;
    private String cpf;
    private String cep;
    private String pais;
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String complemento;
    private String telefone;

    public Customer toCustomer() {
        Customer user = new Customer();
        user.setNome(this.nome);
        user.setEmail(this.email);
        user.setCpf(this.cpf);
        user.setCep(this.cep);
        user.setPais(this.pais);
        user.setEstado(this.estado);
        user.setCidade(this.cidade);
        user.setRua(this.rua);
        user.setNumero(this.numero);
        user.setComplemento(this.complemento);
        user.setTelefone(this.telefone);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}