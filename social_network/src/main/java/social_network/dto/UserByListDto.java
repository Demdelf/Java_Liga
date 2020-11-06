package social_network.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserByListDto {
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
     * Город
     */
    private String city;
}
