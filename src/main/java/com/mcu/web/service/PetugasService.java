package com.mcu.web.service;

import com.mcu.web.models.Petugas;
import com.mcu.web.repository.PetugasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetugasService {

    @Autowired
    private PetugasRepository petugasRepository;

    public String createPetugas(Petugas petugas) {
        if (petugasRepository.findByEmail(petugas.getEmail()) != null) {
            return "Email sudah terdaftar!";
        }

        if (petugasRepository.findByUsername(petugas.getUsername()) != null) {
            return "Username sudah terdaftar!";
        }

        petugasRepository.save(petugas);
        return "Petugas berhasil ditambahkan!";
    }

    public List<Petugas> getAllPetugas() {
        return petugasRepository.findAll();
    }

    public Optional<Petugas> getPetugasById(Long id) {
        return petugasRepository.findById(id);
    }

    public Petugas updatePetugas(Long id, Petugas petugasDetails) {
        Optional<Petugas> petugas = petugasRepository.findById(id);
        if (petugas.isPresent()) {
            Petugas existingPetugas = petugas.get();
            existingPetugas.setUsername(petugasDetails.getUsername());
            existingPetugas.setNama(petugasDetails.getNama());
            existingPetugas.setEmail(petugasDetails.getEmail());
            existingPetugas.setPassword(petugasDetails.getPassword());
            existingPetugas.setJabatan(petugasDetails.getJabatan());
            return petugasRepository.save(existingPetugas);
        }
        return null;
    }

    public boolean deletePetugas(Long id) {
        if (petugasRepository.existsById(id)) {
            petugasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
