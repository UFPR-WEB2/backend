package com.grupo2.demo.dto;

import java.math.BigDecimal;

public class BudgetRequest {
    private BigDecimal precoOrcado;
    private String descricao;
    private String categoria;

    public BudgetRequest() {
    }

    public BudgetRequest(BigDecimal precoOrcado, String descricao, String categoria) {
        this.precoOrcado = precoOrcado;
        this.descricao = descricao;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
