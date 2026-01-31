package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;


import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.AppointmentResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.CreateAppointmentRequestDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.PatientResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.service.AppointmentService;
import com.LearnSpringBoot.InternalWorkingSpringBoot.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")

public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

//    @PostMapping("/appointments")
//    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
//    }

    @GetMapping("/profile/{patientId}")
    private ResponseEntity<PatientResponseDto> getPatientProfile(@PathVariable Long patientId) {
//        Long patientId = 3L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

}
