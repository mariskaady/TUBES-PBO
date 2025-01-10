package com.mcu.web.repository;

import com.mcu.web.models.Petugas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetugasRepository extends JpaRepository<Petugas, Long> {
    Petugas findByEmail(String email);

    Petugas findByUsername(String username);
}
