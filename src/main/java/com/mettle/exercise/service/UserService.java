package com.mettle.exercise.service;

import com.mettle.exercise.exception.RecordNotFoundException;
import com.mettle.exercise.model.CustomUserDetails;
import com.mettle.exercise.model.Feature;
import com.mettle.exercise.model.User;
import com.mettle.exercise.model.request.UserRequest;
import com.mettle.exercise.model.response.UserResponse;
import com.mettle.exercise.repository.FeatureRepository;
import com.mettle.exercise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private FeatureRepository featureRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(userName));
        user.orElseThrow(() -> new UsernameNotFoundException("User Not found: " + userName));
        return user.map(CustomUserDetails::new).get();
    }

    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(mapper(userRepository.findAll()));
    }

    public ResponseEntity<HttpStatus> enableFeature(UserRequest userRequest) {
        User user = userRepository.findByUserName(userRequest.getUserName());
        if (user != null) {
            HashSet<Feature> features = new HashSet<>();
            userRequest.getFeatures().forEach(f -> {
                features.add(getFeatureIfExist(f));
            });
            user.setFeatures(features);
            userRepository.save(user);
        } else {
            throw new RecordNotFoundException(String.format("User: %s not found.", userRequest.getUserName()));
        }
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<UserResponse> getAllFeatureForUser(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
//        Get all the global enabled features
            Set<Feature> globalFeatures = featureRepository.findByGloballyEnabled(true);
            Set<Feature> userFeatures = userRepository.findByUserName(userName).getFeatures();
            return ResponseEntity.ok(new UserResponse(user.getUserName(), Stream.concat(globalFeatures.stream(), userFeatures.stream()).collect(Collectors.toSet())));
        } else {
            throw new RecordNotFoundException(String.format("User: %s not found.", userName));
        }
    }

    //    Validating feature before adding to user
    private Feature getFeatureIfExist(String featureName) {
        Optional<Feature> feature = featureRepository.findByName(featureName);
        if (feature.isEmpty()) {
            throw new RecordNotFoundException(String.format("Feature: %s not found. Please create a feature before assigning to user.", featureName));
        }
        return feature.get();
    }

    //    User to UserResponse mapper util
    private List<UserResponse> mapper(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> {
            userResponses.add(new UserResponse(user.getUserName(), user.getFeatures()));
        });
        return userResponses;
    }

}