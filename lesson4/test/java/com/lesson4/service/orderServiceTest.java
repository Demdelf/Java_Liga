package com.lesson4.service;

import com.lesson4.dao.OrderDao;
import com.lesson4.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit-тесты для OrderService
 */
public class orderServiceTest {
    private OrderService orderService;

    @Mock
    OrderDao orderDao;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderDao);
    }

    @Test
    @DisplayName("Создание заказа")
    void createOrder() throws Exception {
        Order order = new Order("order", 10);
        Mockito.when(orderDao.createOrder(order)).thenReturn(order);
        Assertions.assertEquals(order, orderService.createOrder(order));
    }

}
