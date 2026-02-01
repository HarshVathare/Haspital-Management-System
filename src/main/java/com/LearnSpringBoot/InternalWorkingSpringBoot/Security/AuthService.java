package com.LearnSpringBoot.InternalWorkingSpringBoot.Security;

import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.LoginRequestDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.LoginResponceDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.SignupRequestDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.SignupResponceDTO;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.User;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthUtill authUtill;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, AuthUtill authUtill, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authUtill = authUtill;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public LoginResponceDTO Login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal(); //All data convert to user type
        //After getting user data you can create token easily

        String token = authUtill.generateAccessToken(user);

        return new LoginResponceDTO(token, user.getId());

    }

    public SignupResponceDTO Signup(SignupRequestDTO signupRequestDTO) {

        Optional<User> existingUser = userRepository.findByUsername(signupRequestDTO.getUsername());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User();
        user.setFirstname(signupRequestDTO.getFirstname());
        user.setLastname(signupRequestDTO.getLastname());
        user.setUsername(signupRequestDTO.getUsername());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return new SignupResponceDTO(savedUser.getId(), savedUser.getUsername());
    }



}
