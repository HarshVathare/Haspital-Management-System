package com.LearnSpringBoot.InternalWorkingSpringBoot.service;

import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.DoctorResponseDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.dto.OnboardDoctorRequestDto;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Doctor;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.DoctorRepository;
import jakarta.transaction.Transactional;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
//    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;

    }

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


//    @Transactional
//    public DoctorResponseDto onBoardNewDoctor(OnboardDoctorRequestDto onBoardDoctorRequestDto) {
//        User user = userRepository.findById(onBoardDoctorRequestDto.getUserId()).orElseThrow();
//
//        if(doctorRepository.existsById(onBoardDoctorRequestDto.getUserId())) {
//            throw new IllegalArgumentException("Already a doctor");
//        }
//
//        Doctor doctor = Doctor.builder()
//                .name(onBoardDoctorRequestDto.getName())
//                .specialization(onBoardDoctorRequestDto.getSpecialization())
//                .user(user)
//                .build();
//
//        user.getRoles().add(RoleType.DOCTOR);
//
//        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
//    }
}
