package com.LearnSpringBoot.InternalWorkingSpringBoot.dto;




import java.time.LocalDateTime;

public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private DoctorResponseDto doctor;
//    private PatientResponseDto patient;


    public AppointmentResponseDto() {
    }

    public AppointmentResponseDto(Long id, LocalDateTime appointmentTime, String reason, DoctorResponseDto doctor) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
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

    public DoctorResponseDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorResponseDto doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "AppointmentResponseDto{" +
                "id=" + id +
                ", appointmentTime=" + appointmentTime +
                ", reason='" + reason + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
