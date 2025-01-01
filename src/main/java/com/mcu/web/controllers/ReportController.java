package com.mcu.web.controllers;

import com.mcu.web.models.Transaction;
import com.mcu.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public String getReport(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            // Validasi tanggal
            if (start.isAfter(end)) {
                model.addAttribute("error", "Start date cannot be after end date.");
                return "report";
            }

            List<Transaction> transactions = reportService.getReport(start, end);
            double totalIncome = transactions.stream().mapToDouble(Transaction::getIncome).sum();

            model.addAttribute("transactions", transactions);
            model.addAttribute("totalIncome", totalIncome);
            model.addAttribute("startDate", start);
            model.addAttribute("endDate", end);

        } catch (Exception e) {
            model.addAttribute("error", "Invalid date format. Please use 'YYYY-MM-DD'.");
        }
        return "report";
    }
}
