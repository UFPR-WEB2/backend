package com.grupo2.demo.model.Maintenance;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime data_conserto;
    @Column(nullable = false)
    private String descricao_conserto;
    @Column(nullable = false)
    private String orientacao_cliente;

    public Repair() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_conserto() {
        return data_conserto;
    }

    public void setData_conserto(LocalDateTime data_conserto) {
        this.data_conserto = data_conserto;
    }

    public String getDescricao_conserto() {
        return descricao_conserto;
    }

    public void setDescricao_conserto(String descricao_conserto) {
        this.descricao_conserto = descricao_conserto;
    }

    public String getOrientacao_cliente() {
        return orientacao_cliente;
    }

    public void setOrientacao_cliente(String orientacao_cliente) {
        this.orientacao_cliente = orientacao_cliente;
    }

}
