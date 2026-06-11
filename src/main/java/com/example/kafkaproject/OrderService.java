package com.example.kafkaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

    Map<String, Order> orderDB = new HashMap<>();

    // Step 1 - Create Order
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getOrderId(), "PENDING");
        orderDB.put(order.getOrderId(), order);
        kafkaTemplate.send("order-created-topic",  new OrderCreatedEvent(order.getOrderId()));
        System.out.println( "Order Created: " + order.getOrderId() );
        Order saved = orderRepository.save(order);
        System.out.println("Saved Order: " + saved.getOrderId());
        System.out.println("Total Orders: " + orderRepository.count());
    }

    // Step 2 - Payment Success
    @KafkaListener(
            topics = "payment-success-topic",
            groupId = "order-group"
    )
    public void handlePaymentSuccess( PaymentSuccessEvent event) {
        Order order = orderDB.get(event.getOrderId());
        if (order != null) {
            order.setStatus("CONFIRMED");
            System.out.println( "Order Confirmed: " + order.getOrderId() );
            Order saved = orderRepository.save(order);
            System.out.println("Saved Order: " + saved.getOrderId());
            System.out.println("Total Orders: " + orderRepository.count());
            orderDB.remove(event.getOrderId()) ;
        }
    }

    // Step 2 - Payment Success
    @KafkaListener(
            topics = "payment-failed-topic",
            groupId = "order-group"
    )
    public void handlePaymentFailed( PaymentFailedEvent event) {
        Order order = orderDB.get(event.getOrderId());
        if (order != null) {
            order.setStatus("CANCELLED");
            System.out.println( "Order Cancelled: " + order.getOrderId() );

            Order saved = orderRepository.save(order);
            System.out.println("Saved Order: " + saved.getOrderId());
            System.out.println("Total Orders: " + orderRepository.count());
            orderDB.remove(event.getOrderId()) ;
        }
    }

}
