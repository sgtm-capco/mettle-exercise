package com.mettle.exercise.controller;

import com.mettle.exercise.model.request.UserRequest;
import com.mettle.exercise.model.response.UserResponse;
import com.mettle.exercise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/admin", consumes = "application/json")
    public ResponseEntity<HttpStatus> enableFeature(@Valid @RequestBody UserRequest userRequest) {
        return userService.enableFeature(userRequest);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<UserResponse> getUsers(@PathVariable String userName) {
        return userService.getAllFeatureForUser(userName);
    }

}
