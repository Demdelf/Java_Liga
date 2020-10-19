package lesson7.service;

import lesson7.config.JpaConfig;
import lesson7.domain.Book;
import lesson7.domain.User;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class BasicService {
    /**
     * Пример записи и чтения из БД
     */
    public static void persistExample() {
        /// открываем сессию
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        /*try {
            /// открываем транзакцию
            entityManager.getTransaction().begin();

            /// создаём сущность
            *//*User user = new User(
                    "Dima",
                    "A",
                    "M",
                    new Date(1L)
            );*//*

            Book book = new Book("new");

            /// записываем в базу
            entityManager.persist(book);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }*/

        try {
            /// открываем транзакцию
            entityManager.getTransaction().begin();

            /// создаём сущность
            User user = new User(
                    "Dima",
                    "A",
                    "M",
                    new Date(1L)
            );

            /// записываем в базу
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
