package social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Character sex;
    private String city;
}
