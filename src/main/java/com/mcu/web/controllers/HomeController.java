package com.mcu.web.controllers;

import com.mcu.web.models.User;
import com.mcu.web.service.PaketMCUService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PaketMCUService paketMCUService;

    @GetMapping
    public String showLandingPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // Menambahkan username ke model
            model.addAttribute("user", user);
        }

        model.addAttribute("thorax", paketMCUService.getCheapestPriceByJenis("thorax"));
        model.addAttribute("abdomen", paketMCUService.getCheapestPriceByJenis("abdomen"));
        model.addAttribute("diabetes", paketMCUService.getCheapestPriceByJenis("diabetes"));
        model.addAttribute("tbc", paketMCUService.getCheapestPriceByJenis("tbc"));
        model.addAttribute("skoliosis", paketMCUService.getCheapestPriceByJenis("skoliosis"));
        return "layouts/app";
    }

    @GetMapping("/thorax")
    public String showThorax(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("paketList", paketMCUService.getPaketMCUByJenis("thorax"));
        return "mcu_pasien/paketmcu_detail";
    }

    @GetMapping("/abdomen")
    public String showAbdomen(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("paketList", paketMCUService.getPaketMCUByJenis("abdomen"));
        return "mcu_pasien/paketmcu_detail";
    }

    @GetMapping("/tbc")
    public String showTBC(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("paketList", paketMCUService.getPaketMCUByJenis("tbc"));
        return "mcu_pasien/paketmcu_detail";
    }

    @GetMapping("/skoliosis")
    public String showSkoliosis(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("paketList", paketMCUService.getPaketMCUByJenis("skoliosis"));
        return "mcu_pasien/paketmcu_detail";
    }

    @GetMapping("/diabetes")
    public String showDiabetes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("paketList", paketMCUService.getPaketMCUByJenis("diabetes"));
        return "mcu_pasien/paketmcu_detail";
    }
}
