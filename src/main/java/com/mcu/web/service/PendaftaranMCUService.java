package com.mcu.web.service;

import com.mcu.web.models.PendaftaranMCU;
import com.mcu.web.models.Pasien;
import com.mcu.web.models.PaketMCU;
import com.mcu.web.repository.PendaftaranMCURepository;
import com.mcu.web.repository.PasienRepository;
import com.mcu.web.repository.PaketMCURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PendaftaranMCUService {

    @Autowired
    private PendaftaranMCURepository pendaftaranMCURepository;

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private PaketMCURepository paketMCURepository;

    // Mendaftarkan pasien untuk MCU
    public PendaftaranMCU registerForMCU(Long pasienId, Long paketMCUId) {
        Pasien pasien = pasienRepository.findById(pasienId).orElse(null);
        PaketMCU paketMCU = paketMCURepository.findById(paketMCUId).orElse(null);
        if (pasien == null || paketMCU == null) {
            throw new IllegalArgumentException("Pasien atau Paket MCU tidak valid.");
        }
        PendaftaranMCU pendaftaranMCU = new PendaftaranMCU();
        pendaftaranMCU.setPasien(pasien);
        pendaftaranMCU.setPaketMCU(paketMCU);
        pendaftaranMCU.setTanggalPendaftaran(LocalDate.now());
        return pendaftaranMCURepository.save(pendaftaranMCU);
    }

    // Mengambil pendaftaran berdasarkan ID
    public PendaftaranMCU getPendaftaranById(Long id) {
        // Mencari pendaftaran berdasarkan ID
        return pendaftaranMCURepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pendaftaran dengan ID " + id + " tidak ditemukan"));
    }

    // Mencari pasien yang mendaftar pada periode tertentu
    public List<PendaftaranMCU> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return pendaftaranMCURepository.findByTanggalPendaftaranBetween(startDate, endDate);
    }

    public List<PendaftaranMCU> getPendaftaranByPasien(Long pasienId) {
        Pasien pasien = pasienRepository.findById(pasienId).orElse(null);
        if (pasien != null) {
            return pendaftaranMCURepository.findByPasien(pasien);
        }
        return null;
    }

    public List<PendaftaranMCU> getPendaftaranByPaket(Long paketId) {
        return pendaftaranMCURepository.findByPaketMCU(paketId);
    }

    public List<PendaftaranMCU> getPendaftaranByPeriode(String startDate, String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return pendaftaranMCURepository.findByTanggalPendaftaranBetween(start, end);
        } catch (Exception e) {
            throw new IllegalArgumentException("Format tanggal tidak valid, pastikan menggunakan format yyyy-MM-dd.");
        }
    }

    // Menghapus pendaftaran berdasarkan ID
    public void deletePendaftaran(Long id) {
        // Memastikan bahwa pendaftaran dengan ID yang diberikan ada
        PendaftaranMCU pendaftaranMCU = pendaftaranMCURepository.findById(id).orElse(null);
        if (pendaftaranMCU != null) {
            pendaftaranMCURepository.delete(pendaftaranMCU); // Menghapus data pendaftaran
        } else {
            throw new IllegalArgumentException("Pendaftaran dengan ID " + id + " tidak ditemukan");
        }
    }
}
