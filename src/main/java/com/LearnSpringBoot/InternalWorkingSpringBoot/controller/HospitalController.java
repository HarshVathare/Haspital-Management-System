package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;

import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.DoctorResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")

public class HospitalController {

    private final DoctorService doctorService;

    public HospitalController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
