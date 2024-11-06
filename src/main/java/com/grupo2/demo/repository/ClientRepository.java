package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo2.demo.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
