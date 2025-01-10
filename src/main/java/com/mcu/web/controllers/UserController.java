package com.mcu.web.controllers;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.PendaftaranMCU;
import com.mcu.web.models.User;
import com.mcu.web.service.PendaftaranMCUService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PendaftaranMCUService pendaftaranMCUService;

    @GetMapping
    public String showDashboardUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null && user.getId() != null) {
            List<PendaftaranMCU> pendaftaranList = pendaftaranMCUService.getPendaftaranMCUByPasienId(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("pendaftaranList", pendaftaranList);

            if (pendaftaranList.isEmpty()) {
                model.addAttribute("showWarning", true);
            } else {
                model.addAttribute("showWarning", false);
            }
            
            return "user/index";
        }
        return "redirect:/";
    }
}
