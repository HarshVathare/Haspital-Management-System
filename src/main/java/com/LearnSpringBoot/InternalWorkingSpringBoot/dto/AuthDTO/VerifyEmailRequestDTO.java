package com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO;

public class VerifyEmailRequestDTO {
    private String token;

    public VerifyEmailRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
