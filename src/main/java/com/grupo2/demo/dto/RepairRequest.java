package com.grupo2.demo.dto;


import java.time.LocalDateTime;

public class RepairRequest {
    private Long idManutencao;
    private LocalDateTime dataConserto;
    private String descricaoConserto;
    private String orientacaoCliente;

    public RepairRequest() {
    }

    public RepairRequest(LocalDateTime dataConserto, String descricaoConserto, String orientacaoCliente) {
        this.dataConserto = dataConserto;
        this.descricaoConserto = descricaoConserto;
        this.orientacaoCliente = orientacaoCliente;
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

    public Long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Long idManutencao) {
        this.idManutencao = idManutencao;
    }
}
