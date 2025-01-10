package com.mcu.web.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class PendaftaranMCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDate booking;

    @ManyToOne
    private Pasien pasien;

    @ManyToOne
    private PaketMCU paketMCU;
}
