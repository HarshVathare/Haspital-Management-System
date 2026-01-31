package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;

import com.LearnSpringBoot.InternalWorkingSpringBoot.Security.AuthService;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.LoginRequestDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.LoginResponceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponceDTO> Login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.Login(loginRequestDTO));
    }
}
