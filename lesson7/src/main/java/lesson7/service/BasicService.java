package lesson7.service;


import lesson7.domain.Message;
import lesson7.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс для проверки функциональности
 */
public class BasicService {

    UserService userService = new UserService();

    MessageService messageService = new MessageService();

    /**
     * Метод для проверки функциональности
     */
    public void example(){


        User user1 = new User("Dima", "A", "M");
        user1.setBirthday(LocalDate.of(1988, 1, 1));
        userService.saveUser(user1);
        User user2 = new User("Ivan", "Ch", "S");
        user2.setBirthday(LocalDate.of(1986, 2, 2));
        userService.saveUser(user2);
        User user3 = new User("Serge", "Pe", "R");
        user3.setBirthday(LocalDate.of(1990, 10, 22));
        userService.saveUser(user3);

        User user4 = new User("Mick", "Pe", "R");
        user4.setBirthday(LocalDate.of(1999, 10, 22));
        userService.saveUser(user4);

        User user5 = new User("Jack", "Pe", "R");
        user5.setBirthday(LocalDate.of(1999, 10, 22));
        userService.saveUser(user5);

        Message message1 = new Message("Hi Ivan", user1, user2, LocalDateTime.now());
        message1 = messageService.saveMessage(message1);
        Message message2 = new Message("Hi Dima", user2, user1, LocalDateTime.now());
        messageService.saveMessage(message2);
        Message message3 = new Message("Hi Serge", user1, user3, LocalDateTime.now());
        messageService.saveMessage(message3);
        Message message4 = new Message("Hi Dima", user3, user1, LocalDateTime.now());
        messageService.saveMessage(message4);
        Message message5 = new Message("Hi Mick", user1, user4, LocalDateTime.now());
        messageService.saveMessage(message5);
        Message message6 = new Message("Hi Dima", user5, user1, LocalDateTime.now());
        messageService.saveMessage(message6);

        System.out.println("All messages of " + user1.toString());
        for (Message m: userService.getAllMessages(user1)
             ) {
            System.out.println(m.toString());
        }

        System.out.println("All dialogs of " + user1.toString());
        for (User u: userService.getUserDialogsFromDao(user1)
             ) {
            System.out.println();

            System.out.println(u.toString());
            System.out.println("Сообщения:");
            for (Message m: userService.getDialogMessages(user1, u)
                 ) {
                System.out.println(m.toString());
            }
        }

//        System.out.println(messageService.getMessageById(message1.getId()).toString());
    }
}
