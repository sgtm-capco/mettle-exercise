package com.mettle.exercise.service;


import com.mettle.exercise.model.User;
import com.mettle.exercise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findUserByUserName() {
//        given
        when(userRepository.findByUserName("sgtm"))
                .thenReturn(new User(1, "sgtm", "password", "ACCESS_ALL_AUTHORITY"));
//        when
        User user = userService.getUserByUserName("sgtm");
//        then
        assertNotNull(user);
        assertEquals(user.getUserName(), "sgtm");
        assertEquals(user.getAuthorities(), "ACCESS_ALL_AUTHORITY");
    }

    @Test
    public void throwUsernameNotFoundException() {
//        given
        when(userRepository.findByUserName("sgtm")).thenReturn(null);
//        when
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByUserName("sgtm");
        });
        assertTrue(exception.getMessage().contains("User Not found: sgtm"));
    }

    @Test
    public void updateUserDetails() {
//        given
        when(userRepository.findByUserName("sgtm"))
                .thenReturn(new User(1, "sgtm", "password", "ACCESS_ALL_AUTHORITY"));
//        when
        User user = userService.updateUserRoles("sgtm", List.of("ACCESS_ALL_AUTHORITY", "READ_AUTHORITY", "WRITE_AUTHORITY"));
//        then
        assertEquals(user.getUserName(), "sgtm");
        assertEquals(user.getAuthorities(), "ACCESS_ALL_AUTHORITY,READ_AUTHORITY,WRITE_AUTHORITY");
    }
}