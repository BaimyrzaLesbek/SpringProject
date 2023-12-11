package com.project.tel_book.controller;

import com.project.tel_book.domain.auth.AuthenticationRequest;
import com.project.tel_book.domain.auth.AuthenticationResponse;
import com.project.tel_book.domain.auth.RegisterRequest;
import com.project.tel_book.domain.auth.RegisterResponse;
import com.project.tel_book.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}