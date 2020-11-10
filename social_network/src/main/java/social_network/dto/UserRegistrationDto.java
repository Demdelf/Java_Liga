package social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
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
}
