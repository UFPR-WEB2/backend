package com.grupo2.demo.exception;

public class ForbiddenException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException(String message) {
        super(message);
    }
}
