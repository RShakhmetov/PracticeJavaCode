package org.example.springmvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springmvc.controller.UserController;
import org.example.springmvc.models.OrderEntity;
import org.example.springmvc.models.UserEntity;
import org.example.springmvc.repository.UserRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

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

        mockMvc.perform(get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect()
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

        when(userService.getUser(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Thomas"))
                .andExpect(jsonPath("$.email").value("peakyblinders@mail.ru"))
                .andExpect(jsonPath("$.orders[0].product").value("Product 1"))
                .andExpect(jsonPath("$.orders[0].totalAmount").value(100.0))
                .andExpect(jsonPath("$.orders[0].status").value("COMPLETED"));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserEntity user = new UserEntity();
        user.setName("Thomas");
        user.setEmail("peakyblinders@mail.ru");

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Thomas"))
                .andExpect(jsonPath("$.email").value("peakyblinders@mail.ru"));
    }
}