package com.mcu.web.controllers;

import com.mcu.web.models.Pasien;
import com.mcu.web.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// mport jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pasien")
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @PostMapping
    public ResponseEntity<Pasien> registerPasien(@RequestBody Pasien pasien) {
        Pasien savedPasien = pasienService.save(pasien);
        return ResponseEntity.ok(savedPasien);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pasien> getPasienById(@PathVariable Long id) {
        Pasien pasien = pasienService.findById(id);
        if (pasien != null) {
            return ResponseEntity.ok(pasien);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Pasien>> searchPasienByNama(@RequestParam String nama) {
        List<Pasien> pasienList = pasienService.searchByNama(nama);
        return ResponseEntity.ok(pasienList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pasien> updatePasien(@PathVariable Long id, @RequestBody Pasien pasienDetails) {
        Pasien updatedPasien = pasienService.update(id, pasienDetails);
        if (updatedPasien != null) {
            return ResponseEntity.ok(updatedPasien);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasien(@PathVariable Long id) {
        pasienService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<Pasien> registerPasienExistingOrNew(@PathVariable Long id, @RequestBody Pasien pasienDetails) {
        Pasien pasien = pasienService.registerPasien(id, pasienDetails);
        return ResponseEntity.ok(pasien);
    }
}
