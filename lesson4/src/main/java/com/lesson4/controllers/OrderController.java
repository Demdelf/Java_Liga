package com.example.lesson4.controllers;

import com.example.lesson4.model.Order;
import com.example.lesson4.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping(value = "/api/v1/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        orderService.save(order);
        return new ResponseEntity<>("Order " + order.getId() + " is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/v1/orders")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
