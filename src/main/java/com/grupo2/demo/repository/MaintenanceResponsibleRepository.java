package com.grupo2.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;

public interface MaintenanceResponsibleRepository extends JpaRepository<MaintenanceResponsible, Long> {
    Optional<MaintenanceResponsible> findByManutencaoId(Long id);
}
