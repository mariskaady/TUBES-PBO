package com.example.medicalcheckup.repository;

import com.example.medicalcheckup.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface to interact with the database for Patient entity
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // You can define custom query methods here if needed
    // For example, to find a patient by name
    // Optional<Patient> findByName(String name);
}
