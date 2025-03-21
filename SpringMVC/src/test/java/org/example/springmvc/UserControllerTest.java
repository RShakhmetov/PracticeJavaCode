package org.example.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springmvc.DTO.User;
import org.example.springmvc.controller.UserController;
import org.example.springmvc.models.OrderEntity;
import org.example.springmvc.models.UserEntity;
import org.example.springmvc.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setName("Thomas");
        user1.setEmail("peakyblinders@mail.ru");

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setName("Arthur");
        user2.setEmail("arthur@mail.ru");

        List<UserEntity> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Thomas"))
                .andExpect(jsonPath("$[0].email").value("peakyblinders@mail.ru"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Arthur"))
                .andExpect(jsonPath("$[1].email").value("arthur@mail.ru"));
    }

    @Test
    public void testGetUserByIdWithDetails() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("Thomas");
        user.setEmail("peakyblinders@mail.ru");

        OrderEntity order1 = new OrderEntity();
        order1.setOrderId(1L);
        order1.setCost(1000L);
        order1.setStatus("COMPLETED");
        order1.setUser(user);

        user.setOrders(List.of(order1));

        when(userService.getUser(user.getId())).thenReturn(user);

        mockMvc.perform(get("/api/v1/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Thomas"))
                .andExpect(jsonPath("$.email").value("peakyblinders@mail.ru"))
                .andExpect(jsonPath("$.orders[0].cost").value(1000L))
                .andExpect(jsonPath("$.orders[0].status").value("COMPLETED"));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Jenkins");
        user.setEmail("lero14y@mail.ru");

        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        when(userService.createUser(user)).thenReturn(userEntity);

        mockMvc.perform(post("/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jenkins"))
                .andExpect(jsonPath("$.email").value("lero14y@mail.ru"));
    }
}
