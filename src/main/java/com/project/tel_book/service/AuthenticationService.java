package com.project.tel_book.service;

import com.project.tel_book.constants.Role;
import com.project.tel_book.domain.auth.AuthenticationRequest;
import com.project.tel_book.domain.auth.AuthenticationResponse;
import com.project.tel_book.domain.auth.RegisterRequest;
import com.project.tel_book.domain.auth.RegisterResponse;
import com.project.tel_book.domain.model.User;
import com.project.tel_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public RegisterResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        com.project.tel_book.events.DataChangeEvent event = new com.project.tel_book.events.DataChangeEvent("UserRegistered", "User with ID " + user.getId() + " registered");
        kafkaTemplate.send("kafka", event.getEventType(), event.toString());

        return new RegisterResponse().builder().email(user.getEmail()).password(request.getPassword()).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        com.project.tel_book.events.DataChangeEvent event = new com.project.tel_book.events.DataChangeEvent("UserAuthenticated", "User with ID " + user.getId() + " authenticated");
        kafkaTemplate.send("kafka", event.getEventType(), event.toString());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}