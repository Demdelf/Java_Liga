package com.example.lesson4.services;

import com.example.lesson4.dao.OrderDao;
import com.example.lesson4.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {


    private final OrderDao orderDao;

    public void save(Order order){
        orderDao.insertOrder(order);
    }
}
