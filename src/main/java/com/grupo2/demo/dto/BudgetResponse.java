package com.grupo2.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetResponse {
    private Long id;
    private BigDecimal precoOrcado;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;
    private String categoria;

    public BudgetResponse() {
    }

    public BudgetResponse(Long id, BigDecimal precoOrcado, String descricao, LocalDate dataCriacao, LocalDate dataAtualizacao, String categoria) {
        this.id = id;
        this.precoOrcado = precoOrcado;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoOrcado() {
        return precoOrcado;
    }

    public void setPrecoOrcado(BigDecimal precoOrcado) {
        this.precoOrcado = precoOrcado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
