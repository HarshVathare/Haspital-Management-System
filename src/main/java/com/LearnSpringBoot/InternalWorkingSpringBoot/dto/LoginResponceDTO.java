package com.LearnSpringBoot.InternalWorkingSpringBoot.dto;

public class LoginResponceDTO {
    String jwt;

    Long userId;


    public LoginResponceDTO(String jwt, Long userId) {
        this.jwt = jwt;
        this.userId = userId;
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
}
