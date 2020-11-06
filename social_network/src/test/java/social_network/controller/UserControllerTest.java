package social_network.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import social_network.domain.User;
import social_network.dto.UserRegistrationDto;
import social_network.service.UserService;

/**
 * Unit-тесты для Controller
 */
public class UserControllerTest {
    private UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    @DisplayName("Создание пользователя")
    void createOrder() throws Exception {
        UserRegistrationDto dto = new UserRegistrationDto();
        User user = new User();
        Mockito.when(userService.convertUserRegistrationDtoToUser(dto, user)).thenReturn(user);
        User userFromDto = userService.convertUserRegistrationDtoToUser(dto, user);
        Mockito.when(userService.create(dto)).thenReturn(user.getId());
        Assertions.assertEquals(userFromDto.getId(), userController.create(dto));
    }
}
