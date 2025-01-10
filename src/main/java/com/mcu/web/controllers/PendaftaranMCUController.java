package com.mcu.web.controllers;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.PendaftaranMCU;
import com.mcu.web.models.User;
import com.mcu.web.service.PaketMCUService;
import com.mcu.web.service.PasienService;
import com.mcu.web.service.PendaftaranMCUService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class PendaftaranMCUController {

    @Autowired
    private PendaftaranMCUService pendaftaranMCUService;

    @Autowired
    private PaketMCUService paketMCUService;

    @Autowired
    private PasienService pasienService;

    @GetMapping("/petugas/reservasi")
    public String showReservasiPetugas(@RequestParam(value = "namaPasien", required = false) String namaPasien,
                                       @RequestParam(value = "namaPaket", required = false) String namaPaket,
                                       @RequestParam(value = "periodeWaktu", required = false) String periodeWaktu,
                                       HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        List<PendaftaranMCU> pendaftaranList;

        // Filter berdasarkan parameter yang ada
        if (namaPasien != null && !namaPasien.isEmpty()) {
            pendaftaranList = pendaftaranMCUService.filterByNamaPasien(namaPasien);
        } else if (namaPaket != null && !namaPaket.isEmpty()) {
            pendaftaranList = pendaftaranMCUService.filterByNamaPaket(namaPaket);
        } else if (periodeWaktu != null && !periodeWaktu.isEmpty()) {
            pendaftaranList = pendaftaranMCUService.filterByPeriodeWaktu(periodeWaktu);
        } else {
            pendaftaranList = pendaftaranMCUService.getAllRegistrationsOrdered(); // Jika tidak ada filter
        }

        model.addAttribute("user", user);
        model.addAttribute("pendaftaranMCU", pendaftaranList);
        return "pendaftaran_mcu/index";
    }


//    Pasien
    @GetMapping("/pendaftaranmcu")
    public String showFormPendaftaran(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        Optional<Pasien> pasienOpt = pasienService.getPasienById(user.getId());

        if (pasienOpt.isPresent()) {
            Pasien pasien = pasienOpt.get();

            PendaftaranMCU pendaftaranMCU = new PendaftaranMCU();
            pendaftaranMCU.setPasien(pasien);

            model.addAttribute("user", user);
            model.addAttribute("pendaftaranMCU", pendaftaranMCU);
            model.addAttribute("pasienList", pasien);
            model.addAttribute("paketList", paketMCUService.getAllPaketMCU());
            return "pendaftaran_mcu/pendaftaran_mcu";
        } else {
            model.addAttribute("error", "Pasien tidak ditemukan");
        }

        return "pendaftaran_mcu/pendaftaran_mcu";
    }

    @PostMapping("/pendaftranmcu")
    public String createPendaftaranMCU(@ModelAttribute("pendaftaranMCU") PendaftaranMCU pendaftaranMCU,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");

        Optional<Pasien> pasienOpt = pasienService.getPasienById(user.getId());
        if (pasienOpt.isPresent()) {
            Pasien pasien = pasienOpt.get();
            pendaftaranMCU.setPasien(pasien);

            String message = pendaftaranMCUService.createPendaftaranMCU(pendaftaranMCU);

            if (message.equals("Pendaftaran Berhasil!")) {
                redirectAttributes.addFlashAttribute("success", message);
                return "redirect:/";
            }
        }

        redirectAttributes.addFlashAttribute("error", "Gagal Melakukan Pendaftaran!");
        return "redirect:/pendaftaranmcu";
    }

//    Petugas
    @GetMapping("/petugas/reservasi/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");

        // Ambil data pendaftaran berdasarkan ID
        Optional<PendaftaranMCU> pendaftaranOpt = pendaftaranMCUService.getPendaftaranMCUByID(id);
        if (pendaftaranOpt.isPresent()) {
            PendaftaranMCU pendaftaran = pendaftaranOpt.get();

            // Ambil ID pasien dari PendaftaranMCU
            Long pasienId = pendaftaran.getPasien().getId();

            // Ambil data pasien berdasarkan ID dari pasienService
            Optional<Pasien> pasienOpt = pasienService.getPasienById(pasienId);
            if (pasienOpt.isPresent()) {
                Pasien pasien = pasienOpt.get();

                // Jika pasien ditemukan, tampilkan form edit
                model.addAttribute("user", user);
                model.addAttribute("pendaftaranMCU", pendaftaran);
                model.addAttribute("pasienList", pasien);
                model.addAttribute("paketList", paketMCUService.getAllPaketMCU());
                return "pendaftaran_mcu/edit";
            } else {
                redirectAttributes.addFlashAttribute("error", "Pasien tidak ditemukan.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Pendaftaran tidak ditemukan.");
        }

        return "redirect:/petugas/reservasi"; // Redirect jika pendaftaran tidak ditemukan
    }


    @PostMapping("/petugas/reservasi/edit/{id}")
    public String updatePendaftaran(
            @PathVariable("id") Long id,
            @ModelAttribute("pendaftaranMCU") PendaftaranMCU pendaftaranMCU,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");

        // Ambil data pendaftaran berdasarkan ID
        Optional<PendaftaranMCU> pendaftaranOpt = pendaftaranMCUService.getPendaftaranMCUByID(id);
        if (pendaftaranOpt.isPresent()) {
            PendaftaranMCU pendaftaran = pendaftaranOpt.get();

            // Ambil ID pasien dari PendaftaranMCU
            Long pasienId = pendaftaran.getPasien().getId();
            // Ambil data pasien berdasarkan ID dari pasienService
            Optional<Pasien> pasienOpt = pasienService.getPasienById(pasienId);
            if (pasienOpt.isPresent()) {
                Pasien pasien = pasienOpt.get();
                pendaftaranMCU.setPasien(pasien);

                PendaftaranMCU updated = pendaftaranMCUService.updatePendaftaranMCU(id, pendaftaranMCU);
                redirectAttributes.addFlashAttribute("success", "Pendaftaran berhasil diperbarui!");
                return "redirect:/petugas/reservasi";
            } else {
                redirectAttributes.addFlashAttribute("error", "Pasien tidak ditemukan");
            }
        }

//        //
//        Optional<Pasien> pasienOpt = pasienService.getPasienById(user.getId());
//        if (pasienOpt.isPresent()) {
//            Pasien pasien = pasienOpt.get();
//            pendaftaranMCU.setPasien(pasien);
//
//            PendaftaranMCU updated = pendaftaranMCUService.updatePendaftaranMCU(id, pendaftaranMCU);
//            redirectAttributes.addFlashAttribute("success", "Pendaftaran berhasil diperbarui!");
//            return "redirect:/petugas/reservasi";
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Pasien tidak ditemukan");
//        }

        return "redirect:/pendaftaranmcu/edit/" + id;
    }

    @GetMapping("/petugas/reservasi/delete/{id}")
    public String deleteResevasi(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = pendaftaranMCUService.deletePendaftaranMCU(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("success", "Hapus Resevasi Berhasil!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Hapus Reservasi Gagal!");
        }

        return "redirect:/petugas/reservasi";
    }
}
