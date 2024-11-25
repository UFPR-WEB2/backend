package com.grupo2.demo.exception;

public class CategoryNotFoundException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
