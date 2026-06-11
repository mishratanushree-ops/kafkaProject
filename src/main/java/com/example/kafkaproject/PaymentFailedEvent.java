package com.example.kafkaproject;

public class PaymentFailedEvent {
    String orderId;

    public PaymentFailedEvent() {
    }

    public PaymentFailedEvent(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
