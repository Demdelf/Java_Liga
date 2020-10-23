package com.lesson4.dao;

import com.lesson4.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Unit-тесты для OrderDao
 */
public class OrderDaoTest {
    private OrderDao orderDao;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private KeyHolder keyHolder;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        orderDao = new OrderDao(jdbcTemplate, keyHolder);
    }

    @Test
    @DisplayName("Создание заказа")
    void createOrder() throws Exception {
        Order order = new Order("order", 10);
        int id = 1;
        Mockito.when(keyHolder.getKey()).thenReturn(id);
        Assertions.assertEquals(id, orderDao.createOrder(order).getId());

    }

}
