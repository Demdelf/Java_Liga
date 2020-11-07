package social_network.service.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import social_network.domain.User;

import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.UUID;

/**
 * Базовая спецификация от которой наследуются другие спецификации
 */
public class BaseSpecification {

    /**
     * Поиск по вхождению
     *
     * @param column колонка таблицы сущности T
     * @param value значения для поиска
     * @return спецификация
     */
    public static <T> Specification<T> like(final String column, final String value) {
        return StringUtils.isEmpty(column) || StringUtils.isEmpty(value)
                ? null
                : (root, query, cb) ->
                cb.like(cb.lower(root.get(column)), "%" + value.toLowerCase() + "%");
    }
    
    /**
     * Поиск по величине поля >= переданного значения
     *
     * @param column колонка таблицы сущности T
     * @param value значения для поиска
     * @return спецификация
     */
    public static <T> Specification<T> greaterThanOrEqualTo(final String column, final Integer value) {
        return StringUtils.isEmpty(column)
                ? null
                : (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get(column), value);
    }
    
    /**
     * Поиск по величине поля < переданного значения
     *
     * @param column колонка таблицы сущности T
     * @param value значения для поиска
     * @return спецификация
     */
    public static <T> Specification<T> lessThan(final String column, final Integer value) {
        return StringUtils.isEmpty(column)
                ? null
                : (root, query, cb) ->
                cb.lessThan(root.get(column), value);
    }

    /**
     * Поиск по эквивалентности полю
     *
     * @param column колонка таблицы сущности T
     * @param flag   значение поля
     * @return спецификация
     */
    public static <T> Specification<T> equal(final String column, final Object flag) {
        return StringUtils.isEmpty(column) || ObjectUtils.isEmpty(flag)
                ? null
                : (root, query, cb) -> cb.equal(root.get(column), flag);
    }

    /**
     * Поиск по несовпадающему UUID
     *
     * @param column колонка таблицы сущности T
     * @param id уникальный идентификационный номер
     * @return спецификация
     */
    public static <T> Specification<T> notEqual(final String column, final UUID id) {
        return StringUtils.isEmpty(column) || ObjectUtils.isEmpty(id)
                ? null
                : (root, query, cb) -> cb.notEqual(root.get(column), id);
    }

    /**
     * Поиск по эквивалентности null.
     *
     * @param column название колонки
     * @return спецификация
     */
    public static <T> Specification<T> isNull(String column) {
        return StringUtils.isEmpty(column)
                ? null
                : (root, query, cb) -> cb.isNull(root.get(column));
    }

    /**
     * Поиск по совпадению в иерархии, от UUID выбранной сущности и ниже по иерархии.
     * Переданные UUID проверяются на вхождение в path.
     * Например, если мы фильтруем по подразделению и выбрали UUID ТЭЦ-5, то вернётся ТЭЦ-5
     * и все дочерние цеха (КТЦ, ЭЦ и т.д.)
     *
     * @param ids идентификаторы сущности
     * @return спецификация
     */
    public static <T> Specification<T> likePath(Collection<UUID> ids) {
        return CollectionUtils.isEmpty(ids)
                ? null
                : (root, query, cb) ->
                cb.or(ids.stream()
                        .map(id -> cb.like(root.get("path"), "%" + id + "%"))
                        .toArray(Predicate[]::new));
    }

    /**
     * Поиск по пренадлежности пользователя сету друзей
     * @param user пользователь
     * @return спецификация
     */
    public static Specification<User> isFriend(User user) {
        return user.getId().equals(null)
                ? null
                : (root, query, cb) ->
                cb.isMember(user, root.get("friends"));
    }


}
