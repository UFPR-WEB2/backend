package com.grupo2.demo.controller;

import com.grupo2.demo.dto.CategoryRequest;
import com.grupo2.demo.dto.CategoryResponse;
import com.grupo2.demo.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> listarCategorias() {
        return categoryService.listarCategorias();
    }

    @PostMapping
    public CategoryResponse criarCategoria(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.criarCategoria(categoryRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> obterCategoriaPorId(@PathVariable Long id) {
        CategoryResponse response = categoryService.obterCategoriaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public CategoryResponse atualizarCategoria(@PathVariable Long id, @RequestBody CategoryRequest detalhesCategoria) {
        return categoryService.atualizarCategoria(id, detalhesCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoryService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
