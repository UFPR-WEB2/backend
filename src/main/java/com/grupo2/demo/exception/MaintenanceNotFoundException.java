package com.grupo2.demo.exception;

public class MaintenanceNotFoundException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public MaintenanceNotFoundException(String message) {
        super(message);
    }
}
