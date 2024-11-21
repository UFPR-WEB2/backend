package com.grupo2.demo.dto;

import java.time.LocalDate;

public class RepairRequest {
    private LocalDate dataConserto;
    private String descricaoConserto;
    private String orientacaoCliente;

    public RepairRequest() {
    }

    public RepairRequest(LocalDate dataConserto, String descricaoConserto, String orientacaoCliente) {
        this.dataConserto = dataConserto;
        this.descricaoConserto = descricaoConserto;
        this.orientacaoCliente = orientacaoCliente;
    }

    public LocalDate getDataConserto() {
        return dataConserto;
    }

    public void setDataConserto(LocalDate dataConserto) {
        this.dataConserto = dataConserto;
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
}
