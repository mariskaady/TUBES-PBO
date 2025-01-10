package com.mcu.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Autowired
    private PetugasInterceptor petugasInterceptor;

    @Autowired
    private UserInterceptor userInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // Menambahkan interceptor untuk rute yang dimulai dengan /petugas
        registry.addInterceptor(petugasInterceptor)
                .addPathPatterns("/petugas/**");  // Semua rute yang berhubungan dengan petugas

        // Menambahkan interceptor untuk rute yang dimulai dengan /user
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/pendaftaranmcu", "/user");  // Semua rute yang berhubungan dengan user

        // Menambahkan pengecualian untuk rute public yang bisa diakses siapa saja
        registry.addInterceptor(petugasInterceptor)
                .excludePathPatterns("/**");  // Mengabaikan interceptor untuk rute public
        registry.addInterceptor(userInterceptor)
                .excludePathPatterns("/**");  // Mengabaikan interceptor untuk rute public
    }
}
