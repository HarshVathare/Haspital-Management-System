package com.LearnSpringBoot.InternalWorkingSpringBoot.dto;


import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.type.BloodGroupType;

public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;
    private Long count;

    public BloodGroupCountResponseEntity() {
    }

    public BloodGroupCountResponseEntity(BloodGroupType bloodGroupType, Long count) {
        this.bloodGroupType = bloodGroupType;
        this.count = count;
    }

    public BloodGroupType getBloodGroupType() {
        return bloodGroupType;
    }

    public void setBloodGroupType(BloodGroupType bloodGroupType) {
        this.bloodGroupType = bloodGroupType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BloodGroupCountResponseEntity{" +
                "bloodGroupType=" + bloodGroupType +
                ", count=" + count +
                '}';
    }
}
