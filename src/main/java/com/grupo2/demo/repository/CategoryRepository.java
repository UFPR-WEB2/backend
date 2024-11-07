package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo2.demo.model.Maintenance.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
