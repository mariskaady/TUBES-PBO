package com.mcu.web.controllers;
package aja
    package aja\
    package aja
import com.mcu.web.models.PaketMCU;
import com.mcu.web.models.Petugas;
import com.mcu.web.models.User;
import com.mcu.web.repository.PaketMCURepository;
import com.mcu.web.service.PaketMCUService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/petugas/paketmcu")
public class PaketMCUController {

    @Autowired
    private PaketMCUService paketMCUService;

    @GetMapping
    public String showPaketMCU(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("paketMCU", paketMCUService.getAllPaketMCU());
        return "paket_mcu/index";
    }

    @GetMapping("/add")
    public String showAddPetugasForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        return "paket_mcu/create";
    }

    @PostMapping("/add")
    public String addPaketMCU(@ModelAttribute("paketMCU") PaketMCU paketMCU, RedirectAttributes redirectAttributes) {
        String message = paketMCUService.createPaketMCU(paketMCU);

        if (message.equals("Paket MCU Berhasil Ditambahkan!")) {
            redirectAttributes.addFlashAttribute("success", message);
            return "redirect:/petugas/paketmcu";
        } else {
            return "redirect:/petugas/paketmcu/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editPaketMCU(@PathVariable("id") Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        Optional<PaketMCU> paketMCU = paketMCUService.getPaketMCUById(id);
        if(paketMCU.isPresent()) {
            model.addAttribute("user", user);
            model.addAttribute("paketMCU", paketMCU.get());
            return "paket_mcu/edit";  // Form untuk mengedit paketMCU
        }
        return "redirect:/petugas/paketmcu";  // Jika paketMCU tidak ditemukan, redirect ke paketMCU list
    }

    @PostMapping("/edit/{id}")
    public String editPetugas(@PathVariable("id") Long id, @ModelAttribute("paketMCU") PaketMCU paketMCU, RedirectAttributes redirectAttributes) {

        PaketMCU updatedPaketMCU = paketMCUService.updatePaketMCU(id, paketMCU);
        if (updatedPaketMCU != null) {
            redirectAttributes.addFlashAttribute("success", "Edit Paket MCU Berhasil!");
            return "redirect:/petugas/paketmcu";  // Redirect ke halaman paketMCU list
        }

        redirectAttributes.addFlashAttribute("error", "Edit Paket MCU Gagal!");
        return "redirect:/petugas/paketmcu";  // Jika update gagal, redirect ke paketMCU list
    }

    @GetMapping("/delete/{id}")
    public String deletePaketMCU(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        boolean deleted = paketMCUService.deletePaketMCU(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("success", "Hapus Paket MCU Berhasil!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Hapus Paket MCU Gagal!");
        }
//        Redirect ke halaman daftar petugas
        return "redirect:/petugas/paketmcu";
    }

}
