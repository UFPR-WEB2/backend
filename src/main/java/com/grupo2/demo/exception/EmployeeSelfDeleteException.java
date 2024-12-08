package com.grupo2.demo.exception;

public class EmployeeSelfDeleteException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public EmployeeSelfDeleteException(String message) {
        super(message);
    }
}