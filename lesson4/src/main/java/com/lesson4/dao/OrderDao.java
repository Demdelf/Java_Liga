package com.example.lesson4.dao;

import com.example.lesson4.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDao {
    private static final String SQL_INSERT_PROFILE =
            "insert into order (name, price, customer_id) values (:name, :price, :customer_id)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public void insertOrder(Order order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", order.getName());
        params.addValue("price", order.getPrice());
        params.addValue("customer_id", order.getCustomer_id());
        jdbcTemplate.update(SQL_INSERT_PROFILE, params);
    }
}
