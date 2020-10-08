package com.example.lesson4.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Order {
    @Id
    private int id;
    private String name;
    private int price;
    private int customer_id;
}
