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

    public CategoryResponse criarCategoria(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setNome_categoria(categoryRequest.getNomeCategoria());
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

    // Precisa modificar o BD para desativar e nao perder a integridade
    public void deletarCategoria(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setNomeCategoria(category.getNome_categoria());
        return response;
    }
}
