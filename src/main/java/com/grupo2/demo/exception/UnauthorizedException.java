package com.grupo2.demo.exception;

public class UnauthorizedException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String message) {
        super(message);
    }
}