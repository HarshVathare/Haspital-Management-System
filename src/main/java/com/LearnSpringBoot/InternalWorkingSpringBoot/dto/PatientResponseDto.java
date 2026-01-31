package com.LearnSpringBoot.InternalWorkingSpringBoot.dto;


import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.type.BloodGroupType;

import java.time.LocalDate;

public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;

    public PatientResponseDto() {
    }

    public PatientResponseDto(Long id, String name, String gender, LocalDate birthDate, BloodGroupType bloodGroup) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bloodGroup = bloodGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BloodGroupType getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroupType bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "PatientResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", bloodGroup=" + bloodGroup +
                '}';
    }
}
