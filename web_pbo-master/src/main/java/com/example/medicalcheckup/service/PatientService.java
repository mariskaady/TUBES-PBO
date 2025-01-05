package com.example.medicalcheckup.service;

import com.example.medicalcheckup.model.Patient;
import com.example.medicalcheckup.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    // Constructor-based injection: No need to annotate with @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Register a new patient
    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get a patient by ID
    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null); // Return null if not found
    }

    // Update patient details
    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            Patient updatedPatient = existingPatient.get();
            updatedPatient.setName(patientDetails.getName());
            updatedPatient.setGender(patientDetails.getGender());
            updatedPatient.setDateOfBirth(patientDetails.getDateOfBirth());
            updatedPatient.setContactNumber(patientDetails.getContactNumber());
            return patientRepository.save(updatedPatient);
        }
        return null; // Return null if not found
    }

    // Delete a patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
