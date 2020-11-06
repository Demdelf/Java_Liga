package social_network.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends Identifiable{

    /**
     * Имя
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "LAST_NAME", nullable = false)
    @NotNull
    private String lastName;

    /**
     * Возраст
     */
    private Integer age;

    /**
     * Пол
     */
    private Character sex;

    /**
     * Интересы
     */
    private String interest;

    /**
     * Город
     */
    @Column(name = "CITY")
    @Size(min=3)
    private String city;

    /**
     * Друзья
     */
    @ManyToMany
    private Set<User> friends;
}
