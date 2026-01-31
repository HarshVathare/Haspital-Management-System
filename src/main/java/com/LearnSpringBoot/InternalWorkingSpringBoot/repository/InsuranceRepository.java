package com.LearnSpringBoot.InternalWorkingSpringBoot.repository;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}