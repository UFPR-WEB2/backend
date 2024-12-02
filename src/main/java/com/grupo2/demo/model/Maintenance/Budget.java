package com.grupo2.demo.model.Maintenance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo2.demo.config.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal precoOrcado;

    @Column(nullable = false)
    private LocalDateTime dataOrcamento;

    @Column(nullable = true)
    private String justificativaRejeicao;

    @Column(nullable = true)
    private LocalDateTime dataRejeicao;

    @Column(nullable = false)
    private LocalDateTime dataRecuperacao;

    @Column(nullable = false)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "id_manutencao", nullable = false)
    private Maintenance maintenance;

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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

}
