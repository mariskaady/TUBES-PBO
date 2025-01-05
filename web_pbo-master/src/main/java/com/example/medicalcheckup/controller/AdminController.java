package com.example.medicalcheckup.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from AdminController!";
    }
}
