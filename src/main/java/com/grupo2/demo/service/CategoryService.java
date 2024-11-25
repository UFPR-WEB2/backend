package com.grupo2.demo.service;

import com.grupo2.demo.dto.CategoryRequest;
import com.grupo2.demo.dto.CategoryResponse;
import com.grupo2.demo.model.Maintenance.Category;
import com.grupo2.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponse> listarCategorias() {
        List<Category> categorias = categoryRepository.findAll();
        return categorias.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<CategoryResponse> listarCategoriasAtivas() {
        List<Category> categorias = categoryRepository.findByAtivo(true);
        return categorias.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public CategoryResponse criarCategoria(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setNome_categoria(categoryRequest.getNomeCategoria());
        category.setAtivo(true);
        Category savedCategory = categoryRepository.save(category);
        return mapToResponse(savedCategory);
    }

    public CategoryResponse obterCategoriaPorId(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
        return mapToResponse(category);
    }

    public CategoryResponse atualizarCategoria(Long id, CategoryRequest detalhesCategoria) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));

        category.setNome_categoria(detalhesCategoria.getNomeCategoria());
        Category categoriaAtualizada = categoryRepository.save(category);
        return mapToResponse(categoriaAtualizada);
    }

    public void deletarCategoria(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com ID: " + id);
        }
        Category category = categoryRepository.findById(id).get();
        category.setAtivo(false);
        categoryRepository.save(category);
    }

    public Category obterCategoriaPorNome(String nomeCategoria) {
        Category category = categoryRepository.findByNomeCategoria(nomeCategoria);
        if (category == null) {
            throw new RuntimeException("Categoria não encontrada com nome: " + nomeCategoria);
        }
        return category;
    }

    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setNomeCategoria(category.getNome_categoria());
        response.setAtivo(category.getAtivo());
        return response;
    }
}
