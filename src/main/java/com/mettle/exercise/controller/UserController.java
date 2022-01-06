package com.mettle.exercise.controller;

import com.mettle.exercise.model.User;
import com.mettle.exercise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/users/{userName}")
    public User getUserDetail(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    @PutMapping("/users/{userName}/roles")
    public User updateUserRoles(@PathVariable String userName,
                                @RequestBody List<String> roles) {
        return userService.updateUserRoles(userName, roles);

    }
}
