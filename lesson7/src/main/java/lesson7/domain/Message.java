package lesson7.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сообщение
 */
@Entity
@Table(name = "MESSAGE")
public class Message {

    /**
     * Идентификатор сообщения
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "lesson7.util.UUIDCustomGenerator")
    private UUID id;

    /**
     * Текст сообщения
     */
    @Column(name = "TEXT")
    private String text;

    /**
     * Время отправки сообщения
     */
    @Column(name = "DATE")
    private LocalDateTime date;

    /**
     * Отправитель
     */
    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    /**
     * Получатель
     */
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    public Message(String text, User sender, User receiver, LocalDateTime date) {
        this.text = text;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", date=" + date +
                ", sender=" + sender.getFirstName() + " " + sender.getLastName() +
                ", receiver=" +  receiver.getFirstName() + " " + receiver.getLastName()  +
                '}';
    }
}
