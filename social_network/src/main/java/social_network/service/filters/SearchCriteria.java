package social_network.service.filters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Критерии поиска по пользователям
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SearchCriteria {

    /**
     * Ключ-поле(имя, фамилия, возраст и тд.)
     */
    private String key;

    /**
     * Вид фильтрации(равно, похоже, больше, меньше и тд.)
     */
    private String operation;

    /**
     * Значение для поля
     */
    private Object value;
}
