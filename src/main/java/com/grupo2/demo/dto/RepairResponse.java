package com.grupo2.demo.dto;

import java.time.LocalDateTime;

public class RepairResponse {
    private Long id;
    private LocalDateTime dataConserto;
    private String descricaoConserto;
    private String orientacaoCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataConserto() {
        return dataConserto;
    }

    public void setDataConserto(LocalDateTime dataConserto) {
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
