package com.grupo2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo2.demo.model.Maintenance.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByAtivo(Boolean ativo);
    Category findByNomeCategoria(String nomeCategoria);
}
