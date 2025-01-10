package com.mcu.web.service;

import com.mcu.web.models.Pasien;
import com.mcu.web.models.User;
import com.mcu.web.repository.PasienRepository;
import com.mcu.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasienRepository pasienRepository;

//    Jika Username sudah terdaftar, kirimkan notif
    public String signup(Pasien pasien) {
        if (userRepository.findByUsername(pasien.getUsername()) != null) {
            return "Username Sudah Terdaftar!";
        }

//        Jika Email sudah terdaftar, kirimkan notif
        if (userRepository.findByEmail(pasien.getEmail()) != null) {
            return "Email Sudah Terdaftar!";
        }

        if (pasien.getEmail().isEmpty() && pasien.getPassword().isEmpty()) {
            return "Masukkan Username, Email dan Password!";
        }

        userRepository.save(pasien);
        pasienRepository.save(pasien);

        return "Signup Berhasil!";
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Jika username dan password cocok, kembalikan user
        }
        return null; // Jika gagal login
    }
}
