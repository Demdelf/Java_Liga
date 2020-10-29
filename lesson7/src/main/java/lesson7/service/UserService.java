package lesson7.service;

import lesson7.dao.UserDao;
import lesson7.domain.Message;
import lesson7.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сервисный слой для пользователя
 */
public class UserService {
    UserDao userDao = new UserDao();

    /**
     * Создание пользователя
     * @param user пользователь для создания
     * @return созданный пользователь
     */
    public User saveUser(User user){
        return userDao.save(user);
    }

    /**
     * Обновление пользователя
     * @param user пользователь для обновления
     * @return обновлённый пользователь
     */
    public User updateUser(User user){
        return userDao.save(user);
    }

    /**
     * Удаление пользователя
     * @param user пользователь для удаления
     * @return успешно ли выполнено удаление
     */
    public boolean deleteUser(User user){
        return userDao.delete(user);
    }

    /**
     * Получение всех пользователей
     * @return список пользователей
     */
    public List<User> getAll(){
        return userDao.getAll();
    }

    /**
     * Получение пользователя по ФИО
     * @param user пользователь
     * @return найденный пользователь
     */
    public User findByFullName(User user){
        return userDao.findByFullName(user);
    }

    /**
     * Получение всех сообщений для данного пользователя
     * @param user пользователь
     * @return список сообщений
     */
    public List<Message> getAllMessages(User user){
        return userDao.getAllMessages(user);
    }




    /**
     * Получение всех пользователей с кем переписывался данный пользователь
     * @param user пользователь
     * @return список пользователей
     */
    public List<User> getUserDialogs(User user){
        List<Message> messages = getAllMessages(user);
        messages.sort((message, message1) -> message.getDate().compareTo(message1.getDate()));
        List<User> users = new ArrayList<>();
        for (Message m: messages
             ) {
            User receiverUser = m.getReceiver();
            checkAndAddUser(user, users, receiverUser);
            User senderUser = m.getSender();
            checkAndAddUser(user, users, senderUser);
        }
        return users;
    }

    /**
     * Получение всех пользователей с кем переписывался данный пользователь
     * @param user пользователь
     * @return список пользователей
     */
    public List<User> getUserDialogsFromDao(User user){
        return userDao.getAllDialogs(user);
    }

    /**
     * Получение всех сообщений между данными пользователями
     * @param user1 первый пользователь
     * @param user2 второй пользователь
     * @return @return список сообщений
     */
    public List<Message> getDialogMessages(User user1, User user2){
        return userDao.getDialogMessages(user1, user2);
    }


    /**
     * Проверка данных пользователей на совпадение и добавление в указанный список в обратном случае
     * @param user1 первый пользователь
     * @param users список пользователей
     * @param user2 второй пользователь
     */
    private void checkAndAddUser(User user1, List<User> users, User user2) {
        if (!user2.equals(user1) && !users.contains(user2))
            users.add(user2);
    }

}
