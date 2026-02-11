package com.LearnSpringBoot.InternalWorkingSpringBoot.repository;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.PasswordResetToken;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(User user);
}