
package social_network.service.filters;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import social_network.domain.User;
import social_network.service.specification.BaseSpecification;


import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Фильтр для реестра пользователей
 */
@Getter
@Setter
@NoArgsConstructor
public class UserFilter implements Filter<User> {

  /**
   * Имя пользователя
   */
  private String firstNameLike;

  /**
   * Фамилия пользователя
   */
  private String lastNameLike;



  @Override
  public Specification<User> toSpecification() {
    return where(BaseSpecification.<User>like("firstName", firstNameLike))
            .and(BaseSpecification.like("lastName", lastNameLike));
  }
}
