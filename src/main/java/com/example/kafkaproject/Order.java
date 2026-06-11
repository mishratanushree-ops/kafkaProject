package com.example.kafkaproject;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    String orderId;
    String status;

    public Order() {
    }

    public Order (String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() { return status; }

}
