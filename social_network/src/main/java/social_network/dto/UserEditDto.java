package social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {
    /**
     * Идентификатор пользователя
     */
    private UUID id;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
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
    private String city;
}
