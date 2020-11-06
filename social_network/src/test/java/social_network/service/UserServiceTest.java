package social_network.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import social_network.domain.User;
import social_network.dto.UserRegistrationDto;
import social_network.repository.UserRepository;

public class UserServiceTest {
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Создание пользователя")
    void createUser() throws Exception{
        UserRegistrationDto userDto = new UserRegistrationDto();
        User user = userService.convertUserRegistrationDtoToUser(userDto, new User());

        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user.getId(), userService.create(userDto));
        Mockito.verify(userRepository, Mockito.times(1))
                .save(user);
    }
}
