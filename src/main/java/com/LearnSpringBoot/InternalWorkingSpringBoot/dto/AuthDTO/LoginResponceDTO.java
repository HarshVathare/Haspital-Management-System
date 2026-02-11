package com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.RefreshToken;

public class LoginResponceDTO {
    String jwt;

    Long userId;

    String refreshToken;

    public LoginResponceDTO(String jwt, Long userId, String refreshToken) {
        this.jwt = jwt;
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public LoginResponceDTO() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
