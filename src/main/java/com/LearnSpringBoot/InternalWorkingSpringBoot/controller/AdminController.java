package com.LearnSpringBoot.InternalWorkingSpringBoot.controller;

import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.PatientResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.service.DoctorService;
import com.LearnSpringBoot.InternalWorkingSpringBoot.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private final PatientService patientService;
//    private final DoctorService doctorService;

    public AdminController(PatientService patientService) {
        this.patientService = patientService;

    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }

//    @PostMapping("/onBoardNewDoctor")
//    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnboardDoctorRequestDto onboardDoctorRequestDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onboardDoctorRequestDto));
//    }
}
