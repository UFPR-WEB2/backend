package com.grupo2.demo.model.Maintenance;

import com.grupo2.demo.config.StatusEnum;

import jakarta.persistence.*;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StatusEnum nomeStatus;

    public Status() {}

    public Status(StatusEnum nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(StatusEnum nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

}
