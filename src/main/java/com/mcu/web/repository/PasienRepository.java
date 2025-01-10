package com.mcu.web.repository;

import com.mcu.web.models.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, Long> {
    Optional<Pasien> findByNama(String nama);
}
