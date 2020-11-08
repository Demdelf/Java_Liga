package social_network.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import social_network.domain.User;
import social_network.dto.UserPageDto;
import social_network.dto.UserRegistrationDto;
import social_network.repository.UserRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    User user;
    private UserRegistrationDto userRegistrationDto;
    
    

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
        user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName("User");
        user.setLastName("Userov");
        user.setAge(25);
        user.setSex('M');
        user.setInterest("Some");
        user.setCity("Kukuevo");
        
    }
    
    @Test
    @DisplayName("Поучение пользователя")
    void testFindOne(){
        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.ofNullable(user));
        UserPageDto userPageDto = userService.findOne(user.getId());
        assertNotNull(userPageDto);
        assertEquals("User", userPageDto.getFirstName());
    }
    
    @Test
    @DisplayName("Поучение пользователя")
    void testFindOne_UserNotFoundException(){
        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.ofNullable(null));
        assertThrows(RuntimeException.class,
                () -> {
                    userService.findOne(user.getId());
                }
        );
    }

    @Test
    @DisplayName("Создание пользователя")
    void testCreate() throws Exception{
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setFirstName("User");
        userDto.setLastName("Userov");
        userDto.setAge(25);
        userDto.setSex('M');
        userDto.setInterest("Some");
        userDto.setCity("Kukuevo");

        UUID userStroredId = userService.create(userDto);
        assertNotNull(userStroredId);
        assertEquals(userStroredId, user.getId());
        verify(userRepository, times(1))
                .save(any(User.class));
    }
}
