package com.mcu.web.service;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.Petugas;
import com.mcu.web.models.User;
import com.mcu.web.repository.PasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasienService {

    @Autowired
    private PasienRepository pasienRepository;

    public Optional<Pasien> getPasienById(Long id) {
        return pasienRepository.findById(id);
    }

    public Optional<Pasien> getPasienByNama(String nama) {
        return pasienRepository.findByNama(nama);
    }

    public Pasien createPasien(Long id, Pasien pasienDetails) {
        Optional<Pasien> existingPasien = pasienRepository.findById(id);
        if (existingPasien.isPresent()) {
            Pasien pasien = existingPasien.get();
            pasien.setNama(pasienDetails.getNama());
            pasien.setAlamat(pasienDetails.getAlamat());
            pasien.setPasien(true);
            pasienRepository.save(pasien);
        }
        return null;
    }
}
