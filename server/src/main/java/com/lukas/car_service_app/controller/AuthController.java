package com.lukas.car_service_app.controller;

import com.lukas.car_service_app.dto.user.LoginResponse;
import com.lukas.car_service_app.dto.user.UserRequest;
import com.lukas.car_service_app.model.User;
import com.lukas.car_service_app.repository.UserRepository;
import com.lukas.car_service_app.service.AuthenticationService;
import com.lukas.car_service_app.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserRequest dto) {
        if (userRepository.findByUsername(dto.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        User registeredUser = authenticationService.signup(dto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserRequest dto) {
        User authenticatedUser = authenticationService.authenticate(dto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
