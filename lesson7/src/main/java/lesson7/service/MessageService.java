package lesson7.service;

import lesson7.dao.MessageDao;
import lesson7.domain.Message;

import java.util.List;

/**
 * Сервисный слой для сообщений
 */
public class MessageService {
    MessageDao messageDao = new MessageDao();

    /**
     * Создание сообщения
     * @param message сообщение
     * @return созданное сообщение
     */
    public Message saveMessage(Message message){
        return messageDao.save(message);
    }

    /**
     * Обновление сообщения
     * @param message сообщение
     * @return обновленное сообщение
     */
    public Message updateMessage(Message message){
        return messageDao.save(message);
    }

    /**
     * Удаление сообщения
     * @param message сообщение для удаления
     * @return успешно ли выполнено удаление
     */
    public void deleteMessage(Message message){
        messageDao.delete(message);
    }

    /**
     * Получение всех сообщений
     * @return список сообщений
     */
    public List<Message> getAll(){
        return messageDao.getAll();
    }

}
