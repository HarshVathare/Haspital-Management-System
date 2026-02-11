package com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO;

public class RefreshTokenRequestDTO {
    private String refreshToken;

    public RefreshTokenRequestDTO(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequestDTO() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
