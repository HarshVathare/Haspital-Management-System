package com.LearnSpringBoot.InternalWorkingSpringBoot.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne(cascade ={CascadeType.ALL,CascadeType.REMOVE})
    @JoinColumn(name = "patient_id", nullable = false) // patient is required and not nullable
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY , cascade ={CascadeType.ALL,CascadeType.REMOVE})

    @JoinColumn(nullable = false)
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime appointmentTime, String reason, Patient patient, Doctor doctor) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor( Doctor doctor) {
        this.doctor = doctor;
    }
}
