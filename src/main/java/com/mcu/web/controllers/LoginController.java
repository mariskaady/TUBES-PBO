package com.mcu.web.controllers;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.User;
import com.mcu.web.service.PasienService;
import com.mcu.web.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasienService pasienService;

    @Autowired

    @GetMapping("/login")
    public String showLogin() {

        return "Login/LoginPage";
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "Login/SignUpPage";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("pasien") Pasien pasien,
                         Model model, RedirectAttributes redirectAttributes) {
        // Mendaftar user baru
        String signupMessage = userService.signup(pasien);

        if (signupMessage.equals("Signup Berhasil!")) {
            redirectAttributes.addFlashAttribute("success", signupMessage);
            return "redirect:/login";  // Arahkan ke halaman login setelah signup berhasil
        } else {
            model.addAttribute("error", signupMessage);  // Tampilkan pesan error
            return "Login/SignUpPage";  // Jika signup gagal, tampilkan halaman signup lagi
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,  // Menyimpan session
                        RedirectAttributes redirectAttributes) {
        if (email.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Email Atau Password Tidak Valid!");
            return "redirect:/login"; // Kembali ke halaman login
        }

//        Memeriksa apakah username dan password valid
        User user = userService.login(email, password);

        if (user != null) {
            // Jika login berhasil, simpan user dalam session
            session.setAttribute("user", user);
            if (user.getRole().equals("user")) {
                return "redirect:/";  // Redirect ke halaman beranda pasien
            } else if (user.getRole().equals("petugas")) {
                return "redirect:/petugas";  // Redirect ke halaman dashboard petugas
            }
        } else {
            // Jika login gagal, tampilkan pesan error
            redirectAttributes.addFlashAttribute("error", "Email atau password salah!");
            return "redirect:/login";  // Kembali ke halaman login dengan pesan error
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Menghapus session user
        session.invalidate();

        // Menambahkan pesan ke RedirectAttributes untuk ditampilkan setelah logout
        redirectAttributes.addFlashAttribute("success", "You have been logged out successfully.");

        // Redirect ke halaman login setelah logout
        return "redirect:/login";
    }
}
