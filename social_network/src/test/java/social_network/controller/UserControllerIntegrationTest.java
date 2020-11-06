package social_network.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import social_network.domain.User;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @DisplayName("Сохранение заказа")
    public void createUser() throws Exception {
        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        user.setFirstName("Tom");
        user.setLastName("Doe");
        user.setAge(26);
        mvc.perform(post("/users/" + uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": \"" + uuid.toString() + "\",\n" +
                        "    \"firstName\": \"Tom\",\n" +
                        "    \"lastName\": \"Doe\",\n" +
                        "    \"age\": 26,\n" +
                        "    \"sex\": null,\n" +
                        "    \"interest\": null,\n" +
                        "    \"city\": null,\n" +
                        "    \"friends\": null\n" +
                        "}"));
    }
}
