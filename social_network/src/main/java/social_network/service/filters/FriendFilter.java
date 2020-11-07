package social_network.service.filters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import social_network.domain.User;
import social_network.service.specification.BaseSpecification;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Фильтр для реестра друзей
 */
@Getter
@Setter
@RequiredArgsConstructor
public class FriendFilter extends UserFilter {

    private User user;

    @Override
    public Specification<User> toSpecification() {
        return where(BaseSpecification.isFriend(user)).and(super.toSpecification());
    }
}
