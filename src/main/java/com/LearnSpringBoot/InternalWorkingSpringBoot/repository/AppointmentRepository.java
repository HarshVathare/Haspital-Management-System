package com.LearnSpringBoot.InternalWorkingSpringBoot.repository;


import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}