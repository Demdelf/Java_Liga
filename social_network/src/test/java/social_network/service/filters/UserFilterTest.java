package social_network.service.filters;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import social_network.domain.User;
import social_network.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;


import java.util.List;
import java.util.UUID;

public class UserFilterTest {
    @Mock
    private UserRepository userRepository;

    private User userJohn;
    private User userTom;
    private List<User> allUsers;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        userJohn = new User();
        userJohn.setId(UUID.randomUUID());
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setAge(22);
        allUsers.add(userJohn);

        userTom = new User();
        userTom.setFirstName("Tom");
        userTom.setLastName("Doe");
        userTom.setAge(26);
        allUsers.add(userTom);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserFilter filter =
                new UserFilter();
        filter.setFirstNameLike("");
        filter.setLastNameLike("doe");


        Assertions.assertTrue(allUsers.contains(userJohn));

    }
}
