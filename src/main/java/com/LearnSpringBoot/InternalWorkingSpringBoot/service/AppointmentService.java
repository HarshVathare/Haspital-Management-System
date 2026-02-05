package com.LearnSpringBoot.InternalWorkingSpringBoot.service;


import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AppointmentResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.CreateAppointmentRequestDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Appointment;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Doctor;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Patient;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.AppointmentRepository;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.DoctorRepository;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.LearnSpringBoot.InternalWorkingSpringBoot.config.AppConfig;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));
//        Appointment appointment = Appointment.builder()
//                .reason(createAppointmentRequestDto.getReason())
//                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
//                .build();
        Appointment appointment = new Appointment();
        appointment.setReason(createAppointmentRequestDto.getReason());
        appointment.setAppointmentTime(createAppointmentRequestDto.getAppointmentTime());

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        patient.getAppointments().add(appointment); // to maintain consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Transactional
//    @PreAuthorize("hasAuthority('appointment:write') or #doctorId == authentication.principal.id")
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update, because it is dirty

        doctor.getAppointments().add(appointment); // just for bidirectional consistency

        return appointment;
    }

//    @PreAuthorize("hasRole('ADMIN') OR (hasRole('DOCTOR') AND #doctorId == authentication.principal.id)")
    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
