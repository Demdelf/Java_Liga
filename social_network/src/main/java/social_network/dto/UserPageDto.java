package social_network.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import social_network.domain.User;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserPageDto {
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
