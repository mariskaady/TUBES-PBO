package com.example.medicalcheckup.repository;

import com.example.medicalcheckup.model.Paket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaketRepository extends JpaRepository<Paket, Long> {
    // JpaRepository provides built-in methods like save, deleteById, findAll, findById
}
