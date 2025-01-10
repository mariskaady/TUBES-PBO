package com.mcu.web.configuration;

import com.mcu.web.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PetugasInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Mengecek apakah ada session user
        Object user = request.getSession().getAttribute("user");

        if (user != null && "petugas".equals(((User) user).getRole())) {
            // Jika role adalah petugas, lanjutkan permintaan
            return true;
        }

        // Jika bukan petugas, redirect ke halaman login
        response.sendRedirect("/login");
        return false;
    }
}
