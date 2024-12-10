package com.grupo2.demo.dto;



import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo2.demo.config.StatusEnum;

public class MaintenanceResponse {
    private Long id;
    private String descricaoEquipamento;
    private String descricaoDefeito;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;
    private LocalDateTime dataConserto;
    private String nomeCategoria;
    private StatusEnum nomeStatus;
    private String nomeCliente;
    private String emailCliente;
    private String nomeFuncionario;
    private String descricaoConserto;
    private String orientacaoCliente;
    private BigDecimal valorConserto;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoEquipamento() {
        return descricaoEquipamento;
    }

    public void setDescricaoEquipamento(String descricaoEquipamento) {
        this.descricaoEquipamento = descricaoEquipamento;
    }

    public String getDescricaoDefeito() {
        return descricaoDefeito;
    }

    public void setDescricaoDefeito(String descricaoDefeito) {
        this.descricaoDefeito = descricaoDefeito;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public StatusEnum getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(StatusEnum nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getDescricaoConserto() {
        return descricaoConserto;
    }

    public void setDescricaoConserto(String descricaoConserto) {
        this.descricaoConserto = descricaoConserto;
    }

    public String getOrientacaoCliente() {
        return orientacaoCliente;
    }

    public void setOrientacaoCliente(String orientacaoCliente) {
        this.orientacaoCliente = orientacaoCliente;
    }

    public LocalDateTime getDataConserto() {
        return dataConserto;
    }

    public void setDataConserto(LocalDateTime dataConserto) {
        this.dataConserto = dataConserto;
    }

    public BigDecimal getValorConserto() {
        return valorConserto;
    }

    public void setValorConserto(BigDecimal valorConserto) {
        this.valorConserto = valorConserto;
    }
    
}
