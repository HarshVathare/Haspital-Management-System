package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;

import com.LearnSpringBoot.InternalWorkingSpringBoot.Security.AuthService;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO.*;
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

    @PostMapping("/register")
    public ResponseEntity<SignupResponceDTO> Signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        return ResponseEntity.ok(authService.Signup(signupRequestDTO));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponceDTO> RefreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return ResponseEntity.ok(authService.getRefreshToken(refreshTokenRequestDTO));
    }

    //Verify email
    @PostMapping("/verify-email")
    public ResponseEntity<MessageResponseDTO> verifyEmail(@RequestBody VerifyEmailRequestDTO verifyEmailRequestDTO) {
        return ResponseEntity.ok(authService.verifyEmail(verifyEmailRequestDTO.getJwt()));
    }

    // Forget Password
    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponseDTO> forgotPassword(@RequestBody ForgetPasswordRequestDTO forgetPasswordRequestDTO) {
        return ResponseEntity.ok(authService.forgotPassword(forgetPasswordRequestDTO.getEmail()));
    }

    // Reset Password
    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponseDTO> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        return ResponseEntity.ok(authService.resetPassword(resetPasswordRequestDTO.getToken(), resetPasswordRequestDTO.getNewPassword()));
    }

    // Reset Password
    @PostMapping("/logout")
    public ResponseEntity<MessageResponseDTO> Logout(@RequestBody Logout logout) {
        return ResponseEntity.ok(authService.Logout(logout.getRefreshToken()));
    }
}
