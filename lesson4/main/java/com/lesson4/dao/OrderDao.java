package com.lesson4.dao;

import com.lesson4.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * DAO для обработки заказов
 */
@Repository
@RequiredArgsConstructor
public class OrderDao {
    /**
     * Шаблон строки для запроса в базу данных
     */
    private static final String SQL_INSERT =
            "INSERT INTO `Order` (`name`, `price`, `customer_id`) VALUES (?, ? , ?);";
    /**
     * Исполнитель запросов к базе данных
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Добавляет заказ в базу данных
     * @param order заказ
     * @return заказ добавленный в базу данных
     */
    public Order createOrder(Order order) {
        jdbcTemplate.update(SQL_INSERT, order.getName(), order.getPrice(), order.getCustomer_id());
        order.setId(getOrderId(order.getName()));
        return order;
    }

    /**
     * Возвращает id заказа по названию
     * @param orderName название заказа
     * @return id заказа
     */
    public int getOrderId(String orderName){
        String sql = "SELECT `id` FROM `Order` where `name` = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { orderName }, Integer.class);
    }
}

