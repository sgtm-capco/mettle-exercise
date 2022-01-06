package com.mettle.exercise;

import com.mettle.exercise.model.Feature;
import com.mettle.exercise.model.User;
import com.mettle.exercise.repository.FeatureRepository;
import com.mettle.exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class ExerciseApplication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeatureRepository featureRepository;

    @PostConstruct
    private void populateUsersAndBooks() {

        List users = List.of(new User(null, "admin", "password", "ADMIN", Collections.emptySet()),
                new User(null, "user", "password", "USER", Collections.emptySet()));
        userRepository.saveAll(users);

        List features = List.of(new Feature(null, "Feature 1", true),
                new Feature(null, "Feature 2", false),
                new Feature(null, "Feature 3", false),
                new Feature(null, "Feature 4", true));
        featureRepository.saveAll(features);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }
}
