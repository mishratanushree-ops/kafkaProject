package com.example.kafkaproject;

public class OrderCreatedEvent {
    String orderId;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

}
