package com.grupo2.demo.exception;

public class BudgetNotFoundException extends NullPointerException {

    private static final long serialVersionUID = 1L;

    public BudgetNotFoundException(String message) {
        super(message);
    }
}
