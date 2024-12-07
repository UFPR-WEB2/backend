package com.grupo2.demo.exception;

public class MaintenanceUnprocessableException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public MaintenanceUnprocessableException(String message) {
        super(message);
    }
}
