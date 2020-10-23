package com.lesson4.controller;

import com.lesson4.domain.Order;
import com.lesson4.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit-тесты для Controller
 */
public class OrderControllerTest {
    private OrderController orderController;

    @Mock
    OrderService orderService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        orderController = new OrderController(orderService);
    }

    @Test
    @DisplayName("Создание заказа")
    void createOrder() throws Exception {
        Order order = new Order("order", 10);
        Mockito.when(orderService.createOrder(order)).thenReturn(order);
        Assertions.assertEquals(new ResponseEntity<>(order, HttpStatus.OK), orderController.createOrder(order));
    }
}
