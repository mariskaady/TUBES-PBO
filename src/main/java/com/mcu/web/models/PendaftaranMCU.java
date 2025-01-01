package com.mcu.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "pendaftaran_mcu")
public class PendaftaranMCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pasien tidak boleh null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasien_id", nullable = false)
    private Pasien pasien;

    @NotNull(message = "Paket MCU tidak boleh null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paket_mcu_id", nullable = false)
    private PaketMCU paketMCU;

    @NotNull(message = "Tanggal pendaftaran tidak boleh null")
    private LocalDate tanggalPendaftaran;

    // === Constructor ===
    public PendaftaranMCU() {
        // Default constructor
    }

    public PendaftaranMCU(Pasien pasien, PaketMCU paketMCU, LocalDate tanggalPendaftaran) {
        this.pasien = pasien;
        this.paketMCU = paketMCU;
        this.tanggalPendaftaran = tanggalPendaftaran;
    }

    // === Getters and Setters ===
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public PaketMCU getPaketMCU() {
        return paketMCU;
    }

    public void setPaketMCU(PaketMCU paketMCU) {
        this.paketMCU = paketMCU;
    }

    public LocalDate getTanggalPendaftaran() {
        return tanggalPendaftaran;
    }

    public void setTanggalPendaftaran(LocalDate tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
    }

    // === Override toString ===
    @Override
    public String toString() {
        return "PendaftaranMCU{" +
                "id=" + id +
                ", pasien=" + (pasien != null ? pasien.getNama() : "null") +
                ", paketMCU=" + (paketMCU != null ? paketMCU.getNama() : "null") +
                ", tanggalPendaftaran=" + tanggalPendaftaran +
                '}';
    }
}
