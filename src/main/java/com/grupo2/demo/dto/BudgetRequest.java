package com.grupo2.demo.dto;

import java.math.BigDecimal;

public class BudgetRequest {
    private BigDecimal precoOrcado;
    private String descricao;

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
}
