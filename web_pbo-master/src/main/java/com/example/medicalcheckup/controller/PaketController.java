package com.example.medicalcheckup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medicalcheckup.model.Paket;
import com.example.medicalcheckup.service.PaketService;

@Controller
public class PaketController {

    @Autowired
    private PaketService paketService;

    @GetMapping("/paket-mcu/create")
    public String showCreateForm() {
        return "create"; // Pastikan halaman create.html ada
    }
    // Display the list of Paket MCU
    @GetMapping("/paket-mcu")
    public String listPakets(Model model) {
        model.addAttribute("pakets", paketService.getAllPakets()); // Mengambil daftar paket dari service
        return "paket-mcu"; // Mengarahkan ke halaman paket-mcu.html
    }

    // Handle the Edit Paket action
    @GetMapping("/paket-mcu/edit/{id}")
    public String editPaket(@PathVariable Long id, Model model) {
        Paket paket = paketService.getPaketById(id);
        model.addAttribute("paket", paket);
        return "edit-paket";
    }

    // Handle Delete Paket action
    @PostMapping("/paket-mcu/delete/{id}")
    public String deletePaket(@PathVariable Long id) {
        paketService.deletePaket(id);
        return "redirect:/paket-mcu";
    }

    // Handle Create Paket action via Form
    @PostMapping("/paket-mcu/create")
    public String createPaket(@RequestParam String namaPaket, 
                              @RequestParam String jenisPemeriksaan, 
                              @RequestParam double harga) {
        // Membuat objek Paket baru dan menyimpan ke database
        Paket paket = new Paket(namaPaket, jenisPemeriksaan, harga);
        paketService.savePaket(paket);  // Pastikan metode savePaket ada di service kamu

        // Setelah berhasil, kembali ke halaman dashboard dengan daftar paket yang terbaru
        return "redirect:/paket-mcu";
    }

    

    
}
