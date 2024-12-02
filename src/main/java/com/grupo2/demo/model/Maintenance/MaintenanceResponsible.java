package com.grupo2.demo.model.Maintenance;

import java.time.LocalDate;

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
    private LocalDate data_redirecionamento;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Employee funcionario;

    @ManyToOne
    @JoinColumn(name = "id_manutencao", nullable = false)
    private Maintenance manutencao;

    @ManyToOne
    @JoinColumn(name = "id_reparo", nullable = false)
    private Repair reparo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_redirecionamento() {
        return data_redirecionamento;
    }

    public void setData_redirecionamento(LocalDate data_redirecionamento) {
        this.data_redirecionamento = data_redirecionamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public Employee getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Employee funcionario) {
        this.funcionario = funcionario;
    }

    
    
}
