package com.example.kafkaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(
            topics = "order-created-topic",
            groupId = "payment-group"
    )
    public void handleOrderCreated( OrderCreatedEvent event) {
        try {
            System.out.println("Processing Payment for Order: " + event.getOrderId());

            // Simulate payment processing
            boolean paymentSuccess = new Random().nextBoolean();
            if (paymentSuccess) {
                System.out.println( "Payment Success for Order: "+ event.getOrderId() );
                kafkaTemplate.send("payment-success-topic",  new PaymentSuccessEvent( event.getOrderId()));
            } else {
                throw new RuntimeException( "Payment Failed" );
            }
        } catch (Exception e) {
            System.out.println( "Payment Failed for Order: " + event.getOrderId() );
            kafkaTemplate.send( "payment-failed-topic", new PaymentFailedEvent( event.getOrderId()));
        }
    }
}
