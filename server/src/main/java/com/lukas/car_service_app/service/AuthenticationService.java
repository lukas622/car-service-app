package com.lukas.car_service_app.service;
import com.lukas.car_service_app.dto.user.UserRequest;
import com.lukas.car_service_app.model.Role;
import com.lukas.car_service_app.model.User;
import com.lukas.car_service_app.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserRequest input) {
        User user = new User();

        user.setUsername(input.username());
        user.setPassword(passwordEncoder.encode(input.password()));

        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName("ROLE_USER");


        user.setRoles(List.of(userRole));

        return userRepository.save(user);
    }

    public User authenticate(UserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );

        return userRepository.findByUsername(input.username());
    }
}