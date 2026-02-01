package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;

import com.LearnSpringBoot.InternalWorkingSpringBoot.Security.AuthService;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.LoginRequestDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.LoginResponceDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.SignupRequestDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.SignupResponceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signup")
    public ResponseEntity<SignupResponceDTO> Signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        return ResponseEntity.ok(authService.Signup(signupRequestDTO));
    }

}
