package com.mcu.web.service;

import com.mcu.web.models.Transaction;
import com.mcu.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getReport(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }
}
