package com.mcu.web.controllers;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.User;
import com.mcu.web.service.PasienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @GetMapping("/pendaftaranpasien")
    public String showPendaftaranPasien(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        // Cek apakah user adalah pasien
        if (user instanceof Pasien) {
            Pasien pasien = (Pasien) user;
            model.addAttribute("user", user);
            model.addAttribute("pasien", pasien);
            return "pasien/pendaftaran_pasien";
        }

        return "pasien/pendaftaran_pasien";
    }

    @PostMapping("/pendaftaranpasien")
    public String createPasien(@ModelAttribute("pasien") Pasien pasien, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Pasien pasienUpdate = pasienService.createPasien(user.getId(), pasien);
        if (pasienUpdate != null) {
            session.setAttribute("user", pasienUpdate); // Update user di session
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
