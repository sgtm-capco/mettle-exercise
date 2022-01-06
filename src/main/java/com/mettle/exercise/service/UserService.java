package com.mettle.exercise.service;

import com.mettle.exercise.model.CustomUserDetails;
import com.mettle.exercise.model.User;
import com.mettle.exercise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(userName));
        user.orElseThrow(() -> new UsernameNotFoundException("User Not found: " + userName));
        return user.map(CustomUserDetails::new).get();
    }

    public User getUserByUserName(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(userName));
        return user.orElseThrow(() -> new UsernameNotFoundException("User Not found: " + userName));
    }

    public User updateUserRoles(String userName, List<String> roles) throws UsernameNotFoundException {
        User user = getUserByUserName(userName);
        user.setAuthorities(roles.stream().collect(Collectors.joining(",")));
        userRepository.save(user);
        return getUserByUserName(userName);
    }
}