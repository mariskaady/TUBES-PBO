package com.mcu.web.service;

import com.mcu.web.models.PaketMCU;
import com.mcu.web.repository.PaketMCURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaketMCUService {

    @Autowired
    private PaketMCURepository paketMCURepository;

    public PaketMCU save(PaketMCU paketMCU) {
        return paketMCURepository.save(paketMCU);
    }

    public PaketMCU update(Long id, PaketMCU paketMCUDetails) {
        Optional<PaketMCU> paketMCUOptional = paketMCURepository.findById(id);
        if (paketMCUOptional.isPresent()) {
            PaketMCU paketMCU = paketMCUOptional.get();
            paketMCU.setNama(paketMCUDetails.getNama());
            paketMCU.setTipe(paketMCUDetails.getTipe());
            paketMCU.setKategori(paketMCUDetails.getKategori());
            paketMCU.setHarga(paketMCUDetails.getHarga());
            return paketMCURepository.save(paketMCU);
        }
        return null;
    }

    public void delete(Long id) {
        paketMCURepository.deleteById(id);
    }

    public List<PaketMCU> findAll() {
        return paketMCURepository.findAll();
    }

    public PaketMCU findById(Long id) {
        return paketMCURepository.findById(id).orElse(null);
    }

    public List<PaketMCU> findByKategori(String kategori) {
        return paketMCURepository.findByKategoriContainingIgnoreCase(kategori);
    }
}
