package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD:src/main/java/com/grupo2/demo/repository/RepairRepository.java
import com.grupo2.demo.model.Maintenance.Repair;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
=======

import com.grupo2.demo.model.Maintenance.Repair;

@Repository
public interface FixRepository extends JpaRepository<Repair, Long> {
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/repository/FixRepository.java
    
}
