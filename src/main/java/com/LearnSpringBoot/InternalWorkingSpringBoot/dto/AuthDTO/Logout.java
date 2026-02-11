package com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO;

public class Logout {
    private String refreshToken;

    public Logout() {
    }

    public Logout(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
