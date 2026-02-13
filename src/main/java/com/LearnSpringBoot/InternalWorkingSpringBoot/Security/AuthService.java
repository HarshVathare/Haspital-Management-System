package com.LearnSpringBoot.InternalWorkingSpringBoot.Security;

import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO.*;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.*;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.type.RoleType;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.*;
import com.LearnSpringBoot.InternalWorkingSpringBoot.service.RefreshTokenService;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private static final int expireyminit = 15;

//    private final PasswordResetToken passwordResetToken;

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    private final RefreshTokenService refreshTokenService;

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthUtill authUtill;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthService(  RefreshTokenRepository refreshTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, EmailVerificationTokenRepository emailVerificationTokenRepository, RefreshTokenService refreshTokenService, PatientRepository patientRepository, PasswordEncoder passwordEncoder, AuthUtill authUtill, AuthenticationManager authenticationManager, UserRepository userRepository) {
//        this.passwordResetToken = passwordResetToken;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailVerificationTokenRepository = emailVerificationTokenRepository;
        this.refreshTokenService = refreshTokenService;
        this.patientRepository = patientRepository;
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

        //Access token
        String token = authUtill.generateAccessToken(user);

        //Access Refresh token
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

//        String jwt = authUtill.generateAccessToken(user);

        EmailVerificationToken token2 = new EmailVerificationToken();
        token2.setJwt(token);
        token2.setUser(user);
        token2.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        emailVerificationTokenRepository.save(token2);

        //Pass the access and refresh token
        return new LoginResponceDTO(token, user.getId(), refreshToken.getToken() );

    }

    //Service of refresh token
    public RefreshTokenResponceDTO getRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
          RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.getRefreshToken())
                  .map(refreshTokenService::verifyExpiration)
                  .orElseThrow(()->new RuntimeException("Invalid refresh token ..!"));

          User user = refreshToken.getUser();
          String newAccessToken = authUtill.generateAccessToken(user);

          return new RefreshTokenResponceDTO(newAccessToken , refreshToken.getToken());
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
        user.setRoles(Collections.singleton(RoleType.PATIENT));

        User savedUser = userRepository.save(user);

        Patient patient = new Patient();
        patient.setName(user.getUsername());
        patient.setEmail(user.getEmail());
        patient.setUser(user);

        patientRepository.save(patient);


        return new SignupResponceDTO(savedUser.getId(), savedUser.getUsername());
    }


//    public MessageResponseDTO verifyEmail(String jwt) {
//
//        EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository
//                .findByJwt(jwt).orElseThrow(()->new IllegalArgumentException("Invalid Token ..!"));
//
//        if(emailVerificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//            throw new RuntimeException("Token Expired ..! ");
//        }
//
//        User user = emailVerificationToken.getUser();
//        user.setEmail(user.getEmail());
//        userRepository.save(user);
//
//        emailVerificationTokenRepository.delete(emailVerificationToken);
//        return new MessageResponseDTO("Email Verified Successfully ..!");
//    }

    public MessageResponseDTO verifyEmail(String jwt) {

        EmailVerificationToken token = emailVerificationTokenRepository
                .findByJwt(jwt)
                .orElseThrow(() -> new RuntimeException("Token not found in database"));

        if(token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token Expired");
        }


        User user = token.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);

        emailVerificationTokenRepository.delete(token);

        return new MessageResponseDTO("Email Verified Successfully");
    }



    public MessageResponseDTO forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found ..!"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken1 = new PasswordResetToken();
        passwordResetToken1.setToken(token);
        passwordResetToken1.setUser(user);
        passwordResetToken1.setExpiryDate(LocalDateTime.now().plusMinutes(expireyminit));

        passwordResetTokenRepository.save(passwordResetToken1);
        System.out.println("Password reset token "+token);

        return new MessageResponseDTO("Password reset link sent to your email ...!");
    }

    public MessageResponseDTO resetPassword(String token, String newPassword) {
        PasswordResetToken passwordResetToken1 = passwordResetTokenRepository
                .findByToken(token).orElseThrow(()->new IllegalArgumentException("Invalid Token ..!"));

        if(passwordResetToken1.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token Expired ..! ");
        }

        User user = passwordResetToken1.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordResetTokenRepository.delete(passwordResetToken1);
        return new MessageResponseDTO("Password Reset Successfully ..!");
    }

    public MessageResponseDTO Logout(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken).orElseThrow(()->new IllegalArgumentException("Invalid Refresh Token"));

        refreshTokenRepository.delete(token);
        return new MessageResponseDTO("Logged out Successfully ..!");
    }
}
