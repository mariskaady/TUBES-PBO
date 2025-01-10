package com.mcu.web.controllers;

import com.mcu.web.models.Petugas;
import com.mcu.web.models.User;
import com.mcu.web.service.PendaftaranMCUService;
import com.mcu.web.service.PetugasService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/petugas")
public class PetugasController {

    @Autowired
    private PetugasService petugasService;

    @Autowired
    private PendaftaranMCUService pendaftaranMCUService;

    @GetMapping
    public String showDashboard(HttpSession session, Model model) {
//        Memeriksa apakah user yang login adalah petugas
        User user = (User) session.getAttribute("user");

//        Jika user memiliki role petugas, tampilkan halaman dashboard petugas
        model.addAttribute("user", user);
        model.addAttribute("complete", pendaftaranMCUService.getTotalComplete());
        model.addAttribute("pending", pendaftaranMCUService.getTotalPending());
        model.addAttribute("total", pendaftaranMCUService.getTotalHargaComplete());
        model.addAttribute("pendaftaranProgres", pendaftaranMCUService.getPendingRegistrations());
        return "petugas/dashboard"; // Halaman dashboard petugas
    }

    @GetMapping("/managepetugas")
    public String listsPetugas(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Petugas> petugasList = petugasService.getAllPetugas();

        model.addAttribute("user", user);
        model.addAttribute("petugasList", petugasList);
        return "petugas/showPetugas";
    }

    @GetMapping("/managepetugas/add")
    public String showAddPetugasForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        return "petugas/add_petugas";  // Form untuk menambah petugas
    }

    // Menambahkan petugas baru
    @PostMapping("/managepetugas/add")
    public String addPetugas(@ModelAttribute("petugas") Petugas petugas, RedirectAttributes redirectAttributes) {
        String Message = petugasService.createPetugas(petugas);

        if (Message.equals("Petugas berhasil ditambahkan!")) {
            redirectAttributes.addFlashAttribute("success", Message);
            return "redirect:/petugas/managepetugas";  // Arahkan ke halaman showPetugas ketika petugas berhasil
        } else {
            redirectAttributes.addFlashAttribute("error", Message);
            return "redirect:/petugas/managepetugas/add";  // Jika signup gagal, tampilkan halaman signup lagi
        }
    }

    @GetMapping("/managepetugas/edit/{id}")
    public String showEditPetugasForm(HttpSession session, @PathVariable("id") Long id, Model model) {
        User user = (User) session.getAttribute("user");

        Optional<Petugas> petugas = petugasService.getPetugasById(id);
        if (petugas.isPresent()) {
            model.addAttribute("user", user);
            model.addAttribute("petugas", petugas.get());
            return "petugas/edit_petugas";  // Form untuk mengedit petugas
        }
        return "redirect:/petugas/managepetugas";  // Jika petugas tidak ditemukan, redirect ke daftar
    }

    @PostMapping("/managepetugas/edit/{id}")
    public String editPetugas(@PathVariable("id") Long id, @ModelAttribute("petugas") Petugas petugas, RedirectAttributes redirectAttributes) {

        Petugas updatedPetugas = petugasService.updatePetugas(id, petugas);
        if (updatedPetugas != null) {
            redirectAttributes.addFlashAttribute("success", "Edit Petugas Berhasil!");
            return "redirect:/petugas/managepetugas";  // Redirect ke halaman daftar petugas
        }

        redirectAttributes.addFlashAttribute("error", "Edit Petugas Gagal!");
        return "redirect:/petugas/managepetugas";  // Jika update gagal, redirect ke daftar
    }

    @GetMapping("/managepetugas/delete/{id}")
    public String deletePetugas(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        boolean deleted = petugasService.deletePetugas(id);
        if (deleted) {
           redirectAttributes.addFlashAttribute("success", "Hapus petugas berhasil!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Hapus petugas gagal!");
        }
//        Redirect ke halaman daftar petugas
        return "redirect:/petugas/managepetugas";
    }
}
