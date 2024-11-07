package com.grupo2.demo.model.Maintenance;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao_equipamento;

    @Column(nullable = false)
    private String descricao_defeito;

    @Column(nullable = false)
    private LocalDate data_criacao;

    @Column(nullable = false)
    private LocalDate data_finalizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao_equipamento() {
        return descricao_equipamento;
    }

    public void setDescricao_equipamento(String descricao_equipamento) {
        this.descricao_equipamento = descricao_equipamento;
    }

    public String getDescricao_defeito() {
        return descricao_defeito;
    }

    public void setDescricao_defeito(String descricao_defeito) {
        this.descricao_defeito = descricao_defeito;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDate getData_finalizacao() {
        return data_finalizacao;
    }

    public void setData_finalizacao(LocalDate data_finalizacao) {
        this.data_finalizacao = data_finalizacao;
    }
}