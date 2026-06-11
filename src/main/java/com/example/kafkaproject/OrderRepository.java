package com.example.kafkaproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a Spring Data repository.
public interface OrderRepository extends JpaRepository<Order, Long> {

}