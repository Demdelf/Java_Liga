package social_network.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import social_network.dto.UserEditDto;
import social_network.dto.UserRegistrationDto;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration-тесты для Controller
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Сохранение пользователя")
    @Transactional
    @Rollback
    public void createUser() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
    
        userDto.setFirstName("Tom");
        userDto.setLastName("Doe");
        userDto.setAge(26);
        mvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
    }
    
    @Test
    @DisplayName("Сохранение пользователя c отрицательным возрастом")
    @Transactional
    @Rollback
    public void createUserWithBadAge() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
        
        userDto.setFirstName("Tom");
        userDto.setLastName("Doe");
        userDto.setAge(-26);
        mvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("Получение пользователя по id")
    public void testFindOne() throws Exception {
        mvc.perform(get("/users/5d2ce0ee-8254-4dd3-abff-28a63925cec2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": \"5d2ce0ee-8254-4dd3-abff-28a63925cec2\",\n" +
                        "    \"firstName\": \"Petr\",\n" +
                        "    \"lastName\": \"Petrov\",\n" +
                        "    \"age\": 30,\n" +
                        "    \"sex\": null,\n" +
                        "    \"interest\": null,\n" +
                        "    \"city\": null\n" +
                        "}"));
    }
    
    @Test
    @DisplayName("Обновление пользователя")
    @Transactional
    @Rollback
    public void updateUser() throws Exception {
        UserEditDto userDto = new UserEditDto();
        String userId = "5d2ce0ee-8254-4dd3-abff-28a63925cec2";
        userDto.setFirstName("Tom");
        userDto.setLastName("Doe");
        userDto.setAge(26);
        userDto.setSex("M");
        mvc.perform(put("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("User was updated, id: " + userId));
    }
    
    @Test
    @DisplayName("Обновление пользователя с неправильным полом")
    @Transactional
    @Rollback
    public void updateUserWithWrongSex() throws Exception {
        UserEditDto userDto = new UserEditDto();
        String userId = "5d2ce0ee-8254-4dd3-abff-28a63925cec2";
        userDto.setFirstName("Tom");
        userDto.setLastName("Doe");
        userDto.setAge(26);
        userDto.setSex("man");
        mvc.perform(put("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }
    
    
    @Test
    @DisplayName("Добавление друга пользователя")
    @Transactional
    @Rollback
    public void addFriend() throws Exception {
        UUID userId = UUID.fromString("5d2ce0ee-8254-4dd3-abff-28a63925cec7");
        UUID friendId = UUID.fromString("5d2ce0ee-8254-4dd3-abff-28a63925cec8");
        mvc.perform(
                post("/users/add-friend")
                        .contentType("application/json")
                        .param("id", userId.toString())
                        .param("friendId", friendId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Friend was added"));
    
    }
    
    @Test
    @DisplayName("Удаление пользователя")
    @Transactional
    @Rollback
    public void deleteUser() throws Exception {
        String userId = "5d2ce0ee-8254-4dd3-abff-28a63925cec2";
        mvc.perform(
                delete("/users/")
                        .contentType("application/json")
                        .param("id", userId)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("User was deleted"));
    }
    
    @Test
    @DisplayName("Удаление друга пользователя")
    @Transactional
    @Rollback
    public void deleteFriend() throws Exception {
        UUID userId = UUID.fromString("5d2ce0ee-8254-4dd3-abff-28a63925cec7");
        UUID friendId = UUID.fromString("5d2ce0ee-8254-4dd3-abff-28a63925cec8");
        mvc.perform(
                delete("/users/delete-friend")
                        .contentType("application/json")
                        .param("userId", userId.toString())
                        .param("friendId", friendId.toString())
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Friend was deleted"));
        
    }
    
}
