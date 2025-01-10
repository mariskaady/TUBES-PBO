package com.mcu.web.service;

import com.mcu.web.models.PaketMCU;
import com.mcu.web.models.Petugas;
import com.mcu.web.repository.PaketMCURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaketMCUService {

    @Autowired
    private PaketMCURepository paketMCURepository;

    public List<PaketMCU> getAllPaketMCU() {
        return paketMCURepository.findAll();
    }

    public Optional<PaketMCU> getPaketMCUById(Long id) {
        return paketMCURepository.findById(id);
    }

    public List<PaketMCU> getPaketMCUByJenis(String jenis) {
        return paketMCURepository.findByJenis(jenis);
    }

    public String createPaketMCU(PaketMCU paketMCU) {
        paketMCURepository.save(paketMCU);
        return "Paket MCU Berhasil Ditambahkan!";
    }

    public Integer getCheapestPriceByJenis(String jenis) {
        return paketMCURepository.findCheapestPriceByJenis(jenis);
    }

    public PaketMCU updatePaketMCU(Long id, PaketMCU paketMCUDetails) {
        Optional<PaketMCU> existingPaketMCU = paketMCURepository.findById(id);
        if (existingPaketMCU.isPresent()) {
            PaketMCU paketMCU = existingPaketMCU.get();
            paketMCU.setNama(paketMCUDetails.getNama());
            paketMCU.setDeskripsi(paketMCUDetails.getDeskripsi());
            paketMCU.setJenis(paketMCUDetails.getJenis());
            paketMCU.setKategori(paketMCUDetails.getKategori());
            paketMCU.setHarga(paketMCUDetails.getHarga());
            return paketMCURepository.save(paketMCU);
        }
        return null;
    }

    public boolean deletePaketMCU(Long id) {
        if (paketMCURepository.existsById(id)) {
            paketMCURepository.deleteById(id);
            return true;
        }
        return false;
    }

}
