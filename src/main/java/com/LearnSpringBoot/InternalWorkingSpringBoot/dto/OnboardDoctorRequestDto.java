package com.LearnSpringBoot.InternalWorkingSpringBoot.dto;

public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;

    public OnboardDoctorRequestDto() {
    }

    public OnboardDoctorRequestDto(Long userId, String specialization, String name) {
        this.userId = userId;
        this.specialization = specialization;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OnboardDoctorRequestDto{" +
                "userId=" + userId +
                ", specialization='" + specialization + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
