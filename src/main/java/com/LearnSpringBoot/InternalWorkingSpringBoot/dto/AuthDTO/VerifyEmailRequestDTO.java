package com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO;

public class VerifyEmailRequestDTO {
    private String jwt;

    public VerifyEmailRequestDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
