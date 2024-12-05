package com.grupo2.demo.dto;

import java.math.BigDecimal;

public class BudgetRequest {
    private BigDecimal precoOrcado;
    private Long maintenanceId;

    public BudgetRequest() {
    }

    public BudgetRequest(BigDecimal precoOrcado, Long maintenanceId) {
        this.precoOrcado = precoOrcado;
        this.maintenanceId = maintenanceId;
    }

    public BigDecimal getPrecoOrcado() {
        return precoOrcado;
    }

    public void setPrecoOrcado(BigDecimal precoOrcado) {
        this.precoOrcado = precoOrcado;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }
}
