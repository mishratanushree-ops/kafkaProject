package com.example.kafkaproject;

import com.example.kafkaproject.OrderRequest;
import com.example.kafkaproject.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/orders")
public class OrderController {

        @Autowired
        private OrderService orderService;

        @PostMapping
        public String createOrder( @RequestBody OrderRequest request) {
            orderService.createOrder(request);
            return "Order creation Started";
        }
    }
