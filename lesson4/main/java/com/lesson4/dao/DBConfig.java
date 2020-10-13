package com.lesson4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Конфигурируем подключение к базе данных
 */
@Configuration
@PropertySource(value = "classpath:db.properties", ignoreResourceNotFound = true)
public class DBConfig {

    /**
     * Среда выполнения текущего приложения
     */
    @Autowired
    private Environment env;

    /**
     * Создаём bean фабрики для подключения к базе данных
     * @return фабрика для подключения к базе данных
     */
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder;
        dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("jdbc.driver"));
        dataSourceBuilder.url(env.getProperty("jdbc.url"));
        dataSourceBuilder.username(env.getProperty("jdbc.username"));
        dataSourceBuilder.password(env.getProperty("jdbc.password"));
        return dataSourceBuilder.build();
    }

    /**
     * Создаём bean исполнителя запросов к базе данных
     * @return исполнитель запросов к базе данных
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
