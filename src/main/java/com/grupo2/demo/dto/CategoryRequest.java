package com.grupo2.demo.dto;

public class CategoryRequest {
    private String nomeCategoria;

    public CategoryRequest() {
    }

    public CategoryRequest(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
