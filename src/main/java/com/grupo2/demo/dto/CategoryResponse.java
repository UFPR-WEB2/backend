package com.grupo2.demo.dto;

public class CategoryResponse {
    private Long id;
    private String nomeCategoria;

    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String nomeCategoria) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}