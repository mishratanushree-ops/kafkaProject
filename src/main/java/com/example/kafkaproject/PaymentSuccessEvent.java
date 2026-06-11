package com.example.kafkaproject;

public class PaymentSuccessEvent {
    String orderId;

    public PaymentSuccessEvent() {
    }

    public PaymentSuccessEvent(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
