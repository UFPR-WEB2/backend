package com.grupo2.demo.exception;

public class CategoryConflitException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public CategoryConflitException(String message) {
        super(message);
    }
}
