package com.grupo2.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo2.demo.model.Maintenance.Budget;


public interface BudgetRepository extends JpaRepository<Budget, Long> {
    
    @Query("SELECT b FROM Maintenance m JOIN m.orcamento b WHERE m.id = :maintenanceId")
    Optional<Budget> findByMaintenanceId(@Param("maintenanceId") Long maintenanceId);

    Optional<Budget> findById(Long id);
}
