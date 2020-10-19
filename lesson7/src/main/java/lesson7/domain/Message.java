package lesson7.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "lesson7.util.UUIDCustomGenerator")
    private UUID id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "SENDER")
    private UUID sender;

    @Column(name = "RECEIVER")
    private UUID receiver;

    @Column(name = "DATE")
    private Date date;

    public Message(String text, UUID sender, UUID receiver, Date date) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
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

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public void setReceiver(UUID receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
