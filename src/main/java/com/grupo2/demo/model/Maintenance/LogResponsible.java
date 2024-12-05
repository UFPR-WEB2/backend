package com.grupo2.demo.model.Maintenance;

import java.time.LocalDateTime;

import com.grupo2.demo.model.User.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class LogResponsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private LocalDateTime dataRedirecionamento;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Employee funcionario;

    @ManyToOne
    @JoinColumn(name = "id_manutencaoResponsavel", nullable = false)
    private MaintenanceResponsible manutencaoResponsavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_redirecionamento() {
        return dataRedirecionamento;
    }

    public void setData_redirecionamento(LocalDateTime dataRedirecionamento) {
        this.dataRedirecionamento = dataRedirecionamento;
    }

    public Employee getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Employee funcionario) {
        this.funcionario = funcionario;
    }

    public MaintenanceResponsible getManutencaoResponsavel() {
        return manutencaoResponsavel;
    }

    public void setManutencaoResponsavel(MaintenanceResponsible manutencaoResponsavel) {
        this.manutencaoResponsavel = manutencaoResponsavel;
    }

    
}
