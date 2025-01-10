package com.mcu.web.service;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.PendaftaranMCU;
import com.mcu.web.models.User;
import com.mcu.web.repository.PasienRepository;
import com.mcu.web.repository.PendaftaranMCURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PendaftaranMCUService {

    @Autowired
    private PendaftaranMCURepository pendaftaranMCURepository;

    @Autowired
    private PasienRepository pasienRepository;

    public List<PendaftaranMCU> getAllPendaftaranMCU() {
        return pendaftaranMCURepository.findAll();
    }

    public Optional<PendaftaranMCU> getPendaftaranMCUByID(Long id) {
        return pendaftaranMCURepository.findById(id);
    }

    public List<PendaftaranMCU> getPendaftaranMCUByPasienId(Long pasienId) {
        return pendaftaranMCURepository.findByPasienId(pasienId);
    }

    public String createPendaftaranMCU(PendaftaranMCU pendaftaranMCU) {
        if (pendaftaranMCU.getPasien() == null) {
            return "Pasien is required";
        }
        if (pendaftaranMCU.getPaketMCU() == null) {
            return "Paket MCU is required";
        }
        if (pendaftaranMCU.getBooking() == null) {
            return "Booking date is required";
        }

        pendaftaranMCU.setStatus("pending");
        pendaftaranMCURepository.save(pendaftaranMCU);
        return "Pendaftaran Berhasil!";
    }

    public PendaftaranMCU updatePendaftaranMCU(Long id, PendaftaranMCU pendaftaranMCUDetails) {
        Optional<PendaftaranMCU> existingPendaftaranOpt = pendaftaranMCURepository.findById(id);

        if(existingPendaftaranOpt.isPresent()) {
            PendaftaranMCU existingPendaftaran = existingPendaftaranOpt.get();
            existingPendaftaran.setPaketMCU(pendaftaranMCUDetails.getPaketMCU());
            existingPendaftaran.setBooking(pendaftaranMCUDetails.getBooking());
            existingPendaftaran.setStatus(pendaftaranMCUDetails.getStatus());
            return pendaftaranMCURepository.save(existingPendaftaran);
        }
        return null;
    }

    public boolean deletePendaftaranMCU(Long id) {
        if (pendaftaranMCURepository.existsById(id)) {
            pendaftaranMCURepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PendaftaranMCU> filterByNamaPasien(String namaPasien) {
        return pendaftaranMCURepository.findByPasienNama(namaPasien);
    }

    public List<PendaftaranMCU> filterByNamaPaket(String namaPaket) {
        return pendaftaranMCURepository.findByPaketMCUNama(namaPaket);
    }

    public List<PendaftaranMCU> filterByPeriodeWaktu(String periodeWaktu) {
        return pendaftaranMCURepository.findByBooking(LocalDate.parse(periodeWaktu));
    }

    public long getTotalComplete() {
        return pendaftaranMCURepository.countByStatus("complete");
    }

    public long getTotalPending() {
        return pendaftaranMCURepository.countByStatus("pending");
    }

    public Integer getTotalHargaComplete() {
        return pendaftaranMCURepository.getTotalHargaComplete();
    }

    public List<PendaftaranMCU> getPendingRegistrations() {
        return pendaftaranMCURepository.findByStatusOrderByBookingDesc("pending");
    }

    public List<PendaftaranMCU> getAllRegistrationsOrdered() {
        return pendaftaranMCURepository.findAllOrderByStatusAndBooking();
    }
}
