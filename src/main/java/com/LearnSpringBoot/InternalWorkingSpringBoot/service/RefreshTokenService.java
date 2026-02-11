package com.LearnSpringBoot.InternalWorkingSpringBoot.service;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.RefreshToken;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.User;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.RefreshTokenRepository;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${app.refreshTokenExpiration}")
    private Long refreshTokenExpirationMS;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()->new RuntimeException("User not found ..!"));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenExpirationMS));
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().compareTo(Instant.now()) < 0 ) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh Token expired ! Please Login again ..!");
        }
        return token;
    }

}
