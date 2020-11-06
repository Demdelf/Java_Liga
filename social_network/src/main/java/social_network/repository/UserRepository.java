package social_network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import social_network.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, UUID> {
}
