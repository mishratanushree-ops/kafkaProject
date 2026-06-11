package com.example.kafkaproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("order-created-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentSuccessTopic() {
        return new NewTopic("payment-success-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentFailedTopic() {
        return new NewTopic("payment-failed-topic", 1, (short) 1);
    }

}
