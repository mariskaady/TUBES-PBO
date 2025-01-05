package com.mcu.web.controllers;

import com.mcu.web.models.PendaftaranMCU;
import com.mcu.web.service.PendaftaranMCUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pendaftaran")
public class PendaftaranMCUController {

    @Autowired
    private PendaftaranMCUService pendaftaranMCUService;

    // Mendaftarkan pasien untuk MCU
    @PostMapping
    public ResponseEntity<PendaftaranMCU> registerMCU(@RequestBody PendaftaranMCU pendaftaranMCU) {
        try {
            PendaftaranMCU savedPendaftaran = pendaftaranMCUService.registerForMCU(pendaftaranMCU.getPasien().getId(), pendaftaranMCU.getPaketMCU().getId());
            return ResponseEntity.ok(savedPendaftaran);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);  // Handle generic exceptions.
        }
    }

    // Mengambil data pendaftaran MCU berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<PendaftaranMCU> getPendaftaranById(@PathVariable Long id) {
        PendaftaranMCU pendaftaranMCU = pendaftaranMCUService.getPendaftaranById(id);
        if (pendaftaranMCU != null) {
            return ResponseEntity.ok(pendaftaranMCU);
        }
        return ResponseEntity.notFound().build();
    }

    // Mengambil daftar pendaftaran MCU berdasarkan pasien
    @GetMapping("/pasien/{pasienId}")
    public ResponseEntity<List<PendaftaranMCU>> getPendaftaranByPasien(@PathVariable Long pasienId) {
        List<PendaftaranMCU> pendaftaranList = pendaftaranMCUService.getPendaftaranByPasien(pasienId);
        if (pendaftaranList != null && !pendaftaranList.isEmpty()) {
            return ResponseEntity.ok(pendaftaranList);
        }
        return ResponseEntity.noContent().build();
    }

    // Mengambil daftar pendaftaran MCU berdasarkan paket
    @GetMapping("/paket/{paketId}")
    public ResponseEntity<List<PendaftaranMCU>> getPendaftaranByPaket(@PathVariable Long paketId) {
        List<PendaftaranMCU> pendaftaranList = pendaftaranMCUService.getPendaftaranByPaket(paketId);
        return ResponseEntity.ok(pendaftaranList);
    }

    // Mengambil daftar pendaftaran MCU berdasarkan periode tanggal
    @GetMapping("/periode")
    public ResponseEntity<List<PendaftaranMCU>> getPendaftaranByPeriode(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<PendaftaranMCU> pendaftaranList = pendaftaranMCUService.getPendaftaranByPeriode(startDate, endDate);
            return ResponseEntity.ok(pendaftaranList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Menghapus pendaftaran MCU berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePendaftaran(@PathVariable Long id) {
        try {
            pendaftaranMCUService.deletePendaftaran(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
