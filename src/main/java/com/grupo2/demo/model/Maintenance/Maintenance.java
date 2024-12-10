package com.grupo2.demo.model.Maintenance;

import java.time.LocalDateTime;

import com.grupo2.demo.model.User.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
    private LocalDateTime data_criacao;

    @Column(nullable = true)
    private LocalDateTime data_finalizacao;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Customer cliente;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Category categoria;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_orcamento", nullable = true)
    private Budget orcamento;

    @ManyToOne
    @JoinColumn(name = "id_funcionarioResponsavel", nullable = true)
    private MaintenanceResponsible funcionarioResponsavel;

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

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDateTime getData_finalizacao() {
        return data_finalizacao;
    }

    public void setData_finalizacao(LocalDateTime data_finalizacao) {
        this.data_finalizacao = data_finalizacao;
    }

    public Customer getCliente() {
        return cliente;
    }

    public void setCliente(Customer cliente) {
        this.cliente = cliente;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Budget getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Budget orcamento) {
        this.orcamento = orcamento;
    }

    public MaintenanceResponsible getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(MaintenanceResponsible funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    

}
