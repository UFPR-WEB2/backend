package com.grupo2.demo.model.Maintenance;

import com.grupo2.demo.model.User.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MaintenanceResponsible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Employee funcionario;

    @ManyToOne
    @JoinColumn(name = "id_manutencao", nullable = false)
    private Maintenance manutencao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean Status) {
        this.status = Status;
    }

    public Employee getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Employee funcionario) {
        this.funcionario = funcionario;
    }

    public Maintenance getManutencao() {
        return manutencao;
    }

    public void setManutencao(Maintenance manutencao) {
        this.manutencao = manutencao;
    }

}
