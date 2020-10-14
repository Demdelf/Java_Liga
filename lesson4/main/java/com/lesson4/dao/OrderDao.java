package com.lesson4.dao;

import com.lesson4.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

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

    protected KeyHolder keyHolder = new GeneratedKeyHolder();

    /**
     * Добавляет заказ в базу данных
     * @param order заказ
     * @return заказ добавленный в базу данных
     */
    public Order createOrder(Order order) {

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_INSERT, 1);
                    ps.setString(1, order.getName());
                    ps.setInt(2, order.getPrice());
                    ps.setInt(3, order.getCustomer_id());
                    return ps;
                }, keyHolder
        );
        order.setId((int) keyHolder.getKey());
        return order;
    }
}
