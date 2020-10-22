package lesson7.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Пользователь
 */
@Entity
@Table(name = "USERS")
public class User {

    /**
     * Идентификатор пользователя
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "lesson7.util.UUIDCustomGenerator")
    private UUID user_id;

    /**
     * Имя пользователя
     */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * Отчество пользователя
     */
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    /**
     * Дата рождения
     */
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    /**
     * Список полученных сообщений
     */
    @OneToMany(mappedBy = "receiver")
    private List<Message> messagesRecived;

    /**
     * Список отправленных сообщений
     */
    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSended;



    public User(String firstName, String lastName, String middleName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
    }

    public User() {
    }

    public User(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID id) {
        this.user_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
