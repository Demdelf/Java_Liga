package com.lesson4.dao;

import com.lesson4.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit-тесты для OrderDao
 */
public class OrderDaoTest {
    @Mock
    private OrderDao orderDao;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Создание заказа")
    void createOrder() throws Exception {
        Order order = new Order("order", 10);
        Mockito.when(orderDao.createOrder(order)).thenReturn(order);
        Assertions.assertEquals(order, orderDao.createOrder(order));
    }

    @Test
    @DisplayName("Получение id заказа")
    void getOrderId() throws Exception {
        Order order = new Order("order", 10);
        order.setId(1);
        Mockito.when(orderDao.getOrderId(order.getName())).thenReturn(1);
        Assertions.assertEquals(1, orderDao.getOrderId(order.getName()));
    }
}
