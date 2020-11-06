package social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
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
