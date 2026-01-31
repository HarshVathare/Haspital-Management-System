package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;

import com.LearnSpringBoot.InternalWorkingSpringBoot.service.AppointmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")

public class DoctorController {

    private final AppointmentService appointmentService;

    public DoctorController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

//    @GetMapping("/appointments")
//    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(user.getId()));
//    }

}
