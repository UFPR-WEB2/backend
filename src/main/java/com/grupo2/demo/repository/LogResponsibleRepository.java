package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo2.demo.model.Maintenance.LogResponsible;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;

public interface LogResponsibleRepository extends JpaRepository<LogResponsible, Long> {
    LogResponsible  findTopByManutencaoResponsavelOrderByDataRedirecionamentoDesc(MaintenanceResponsible manutencaoResponsavel);
}
