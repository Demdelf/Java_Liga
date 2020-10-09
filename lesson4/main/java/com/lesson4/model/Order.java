package com.lesson4.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

/**
 * Заказ
 */
@Data
public class Order {
    /**
     * Индификатор заказа
     */
    @Id
    private int id;
    /**
     * Название заказа
     */
    @NonNull
    private String name;
    /**
     * Стоимость заказа
     */
    @NonNull
    private int price;

    /**
     * Индификатор клиента, сделавшего заказ
     */
    private int customer_id;
}
