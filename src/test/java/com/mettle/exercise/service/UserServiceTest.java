package com.mettle.exercise.service;


import com.mettle.exercise.model.User;
import com.mettle.exercise.repository.FeatureRepository;
import com.mettle.exercise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private FeatureRepository featureRepository;

    @Test
    public void returnUserDetailsWhenLoadUserByUsernameIsInvoked() {
//        given
        when(userRepository.findByUserName("sgtm"))
                .thenReturn(new User(null, "sgtm", null, "ADMIN", Collections.emptySet()));
//        when
        UserDetails user = userService.loadUserByUsername("sgtm");
//        then
        assertNotNull(user);
        assertEquals("sgtm", user.getUsername());
    }

    @Test
    public void throwUsernameNotFoundException() {
//        given
        when(userRepository.findByUserName("sgtm")).thenReturn(null);
//        when
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("sgtm");
        });
        assertTrue(exception.getMessage().contains("User Not found: sgtm"));
    }
//    TODO: Add more unit test
}