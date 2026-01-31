package com.LearnSpringBoot.InternalWorkingSpringBoot.repository;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}