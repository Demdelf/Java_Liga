package social_network.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import social_network.domain.User;
import social_network.dto.UserPageDto;
import social_network.dto.UserRegistrationDto;
import social_network.service.UserService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit-тесты для Controller
 */
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    UserPageDto userDto;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
//        userController = new UserController(userService);
        userDto = new UserPageDto();
        userDto.setId(UUID.randomUUID());
        userDto.setFirstName("User");
        userDto.setLastName("Userov");
        userDto.setAge(25);
        userDto.setSex('M');
        userDto.setInterest("Some");
        userDto.setCity("Kukuevo");
    }

    @Test
    @DisplayName("Создание пользователя")
    void testCreate() throws Exception {
        UserRegistrationDto dto = new UserRegistrationDto();
        User user = new User();
        
        when(userService.convertUserRegistrationDtoToUser(dto)).thenReturn(user);
        User userFromDto = userService.convertUserRegistrationDtoToUser(dto);
        when(userService.create(dto)).thenReturn(user.getId());
        Assertions.assertEquals(userFromDto.getId(), userController.create(dto));
    }
    
    @Test
    @DisplayName("Получение пользователя")
    void testFindOne() throws Exception {
        when(userService.findOne(any(UUID.class))).thenReturn(userDto);
    
        UserPageDto userPageDto = userController.findOne(UUID.randomUUID());
        assertNotNull(userPageDto);
        assertEquals(userDto.getId(), userPageDto.getId());
        assertEquals(userDto.getFirstName(), userPageDto.getFirstName());
        assertEquals(userDto.getLastName(), userPageDto.getLastName());
    }
}
