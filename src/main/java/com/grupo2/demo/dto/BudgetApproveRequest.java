package com.grupo2.demo.dto;

public class BudgetApproveRequest {
    private Long id;

    public BudgetApproveRequest() {
    }

    public BudgetApproveRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}