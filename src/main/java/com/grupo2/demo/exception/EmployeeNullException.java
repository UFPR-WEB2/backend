package com.grupo2.demo.exception;

public class EmployeeNullException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public EmployeeNullException(String message) {
        super(message);
    }
}
