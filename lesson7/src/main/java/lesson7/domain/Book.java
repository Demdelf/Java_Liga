package lesson7.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "lesson7.util.UUIDCustomGenerator")
    private UUID id;

    @Column(name = "TEXT")
    private String text;

    public Book(String text) {
        this.text = text;
    }

    public Book() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
