package com.mettle.exercise.controller;

import com.mettle.exercise.model.User;
import com.mettle.exercise.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)

public class UserControllerTest {


    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    public void getUserDetails() throws Exception {
        Mockito.when(userService.getUserByUserName("sgtm")).thenReturn(
                new User(1, "test_user", "password", "READ_AUTHORITY"));

        mockMvc.perform(get("/users/test_user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is("1")));
    }
}
