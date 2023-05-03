package com.hormigo.david.parkingmanager.user.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.service.UserService;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Test
    public void shouldDisplayUserList() {
        List<User> users = new ArrayList<>();
        users.add(new User("david@user","David","Hormigo","Ramírez",Role.PROFESSOR));
        when(this.userService.getAll()).thenReturn(users);

        List<User> received = (List<User>) this.userService.getAll();
        try {
            this.mockMvc.perform(get("/users"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("user/list"))
                        .andExpect(model().attributeExists("users"));
        } catch (Exception e) {
    
            e.printStackTrace();
        }
    }
}
