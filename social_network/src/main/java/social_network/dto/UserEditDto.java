package social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 2, max = 15)
    private String firstName;

    /**
     * Фамилия
     */
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    /**
     * Возраст
     */
    @PositiveOrZero
    private Integer age;

    /**
     * Пол
     */
    @Pattern(regexp = "^[M|F]{1}$", message ="Must be M or F")
    private String sex;

    /**
     * Интересы
     */
    private String interest;

    /**
     * Город
     */
    private String city;
}
