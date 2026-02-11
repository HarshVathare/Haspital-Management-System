package com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AuthDTO;

public class ForgetPasswordRequestDTO {
    private String email;

    public ForgetPasswordRequestDTO() {
    }

    public ForgetPasswordRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
