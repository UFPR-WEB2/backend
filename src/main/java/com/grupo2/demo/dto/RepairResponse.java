package com.grupo2.demo.dto;

import java.time.LocalDate;

public class RepairResponse {
    private Long id;
    private LocalDate dataConserto;
    private String descricaoConserto;
    private String orientacaoCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
