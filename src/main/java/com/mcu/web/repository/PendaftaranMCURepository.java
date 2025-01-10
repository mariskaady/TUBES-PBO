package com.mcu.web.repository;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.PendaftaranMCU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PendaftaranMCURepository extends JpaRepository<PendaftaranMCU, Long> {
    List<PendaftaranMCU> findByPasienNama(String namaPasien);

    List<PendaftaranMCU> findByPaketMCUNama(String namaPaket);

    List<PendaftaranMCU> findByBooking(LocalDate periodeWaktu);

    List<PendaftaranMCU> findByPasienId(Long pasienId);

    long countByStatus(String status);

    @Query("SELECT SUM(p.paketMCU.harga) FROM PendaftaranMCU p WHERE p.status = 'complete'")
    Integer getTotalHargaComplete();

    List<PendaftaranMCU> findByStatusOrderByBookingDesc(String status);

    @Query("SELECT p FROM PendaftaranMCU p ORDER BY CASE WHEN p.status = 'pending' THEN 0 ELSE 1 END, p.booking ASC")
    List<PendaftaranMCU> findAllOrderByStatusAndBooking();
}
