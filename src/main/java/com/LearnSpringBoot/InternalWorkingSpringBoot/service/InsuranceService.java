package com.LearnSpringBoot.InternalWorkingSpringBoot.service;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Insurance;
import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.Patient;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.InsuranceRepository;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service

public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    public InsuranceService(InsuranceRepository insuranceRepository, PatientRepository patientRepository) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(insurance);
        //insurance.setPatient(patient); // bidirectional consistency maintainence

        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(null);
        return patient;
    }

    // HW
    //Create three appointment for a patient and then delete Patient


}
