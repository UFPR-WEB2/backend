package com.grupo2.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.model.Maintenance.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByNomeStatus(StatusEnum nomeStatus);
}
