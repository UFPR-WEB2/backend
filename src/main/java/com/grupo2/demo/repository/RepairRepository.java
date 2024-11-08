package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo2.demo.model.Maintenance.Repair;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    
}
