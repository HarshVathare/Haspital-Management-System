package com.LearnSpringBoot.InternalWorkingSpringBoot.service;

import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.DoctorResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.OnboardDoctorRequestDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Doctor;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.User;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.type.RoleType;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.DoctorRepository;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.UserRepository;
import jakarta.transaction.Transactional;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;

        this.userRepository = userRepository;
    }

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnboardDoctorRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (doctorRepository.existsByUserId(dto.getUserId())) {
            throw new IllegalArgumentException("Already a doctor");
        }

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());
        doctor.setUser(user);

        // Add role to user
        user.getRoles().add(RoleType.DOCTOR);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return modelMapper.map(savedDoctor, DoctorResponseDto.class);
    }

}
