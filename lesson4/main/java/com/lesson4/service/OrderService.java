package com.lesson4.service;

import com.lesson4.dao.OrderDao;
import com.lesson4.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки заказов
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    /**
     * Сохраняет созданный заказ
     * @param order созданный заказ
     * @return созданный заказ
     */
    public Order createOrder(Order order){
        return orderDao.createOrder(order);
    }
}
