package com.grupo2.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetResponse {
    private Long id;
    private BigDecimal precoOrcado;
    private LocalDateTime dataOrcamento;
    private String justificativaRejeicao;
    private LocalDateTime dataRejeicao;
    private LocalDateTime dataRecuperacao;
    private Boolean status;
    private Long maintenanceId;

    public BudgetResponse() {
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoOrcado() {
        return precoOrcado;
    }

    public void setPrecoOrcado(BigDecimal precoOrcado) {
        this.precoOrcado = precoOrcado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(LocalDateTime dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public String getJustificativaRejeicao() {
        return justificativaRejeicao;
    }

    public void setJustificativaRejeicao(String justificativaRejeicao) {
        this.justificativaRejeicao = justificativaRejeicao;
    }

    public LocalDateTime getDataRejeicao() {
        return dataRejeicao;
    }

    public void setDataRejeicao(LocalDateTime dataRejeicao) {
        this.dataRejeicao = dataRejeicao;
    }

    public LocalDateTime getDataRecuperacao() {
        return dataRecuperacao;
    }

    public void setDataRecuperacao(LocalDateTime dataRecuperacao) {
        this.dataRecuperacao = dataRecuperacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }
}
