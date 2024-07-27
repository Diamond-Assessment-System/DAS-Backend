package com.project.DASBackend.dto;

public class PaymentRequest {
    private int amount;

    public PaymentRequest() {
    }

    public PaymentRequest(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
