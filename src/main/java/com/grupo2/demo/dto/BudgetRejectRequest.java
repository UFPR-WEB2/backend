package com.grupo2.demo.dto;

public class BudgetRejectRequest {
    private Long id;
    private String justificativaRejeicao;

    public BudgetRejectRequest() {
    }

    public BudgetRejectRequest(Long id, String justificativaRejeicao) {
        this.id = id;
        this.justificativaRejeicao = justificativaRejeicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJustificativaRejeicao() {
        return justificativaRejeicao;
    }

    public void setJustificativaRejeicao(String justificativaRejeicao) {
        this.justificativaRejeicao = justificativaRejeicao;
    }
}