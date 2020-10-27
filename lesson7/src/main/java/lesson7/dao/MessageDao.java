package lesson7.dao;

import lesson7.config.JpaConfig;
import lesson7.domain.Message;
import lesson7.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class MessageDao {
    /**Создание или обновление сообщения
     * @param message сообщение
     * @return сохраннёное сообщение
     */
    public Message save(Message message) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (message.getId() == null) {
                entityManager.persist(message);
            } else {
                message = entityManager.merge(message);
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return message;
    }

    /**
     * Удаление сообщения
     * @param message сообщение для удаления
     * @return успешно ли выполнено удаление
     */
    public boolean delete(Message message) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        boolean result = false;
        try {
            // start the transaction
            entityManager.getTransaction().begin();

            // save a object
            if (message.getId() != null) {
                entityManager.remove(message);
                result = true;
            } else {
                System.out.println("Can't find this message");
            }

            // commit transction
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            throw e;
        }finally {
            entityManager.close();
        }

        return result;
    }

    /**
     * Получение всех сообщений
     * @return список сообщений
     */
    public List<Message> getAll(){
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);

        Root<Message> root = query.from(Message.class);
        query.select(root);
//        entityManager.

        return entityManager.createQuery(query).getResultList();
    }

    public Message getMessageById(UUID id) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        return entityManager.find(Message.class, id);
    }
}
