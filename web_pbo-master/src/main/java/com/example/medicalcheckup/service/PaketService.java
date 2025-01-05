package com.example.medicalcheckup.service;

import com.example.medicalcheckup.model.Paket;
import com.example.medicalcheckup.repository.PaketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaketService {

    @Autowired
    private PaketRepository paketRepository;

    public List<Paket> getAllPakets() {
        return paketRepository.findAll();
    }

    public Paket getPaketById(Long id) {
        return paketRepository.findById(id).orElse(null);
    }

    public void deletePaket(Long id) {
        paketRepository.deleteById(id);
    }

    public Paket savePaket(Paket paket) {
        return paketRepository.save(paket);
    }
}
