package com.mcu.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pasien")
public class Pasien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(min = 2, max = 100, message = "Nama harus antara 2 hingga 100 karakter")
    private String nama;

    @NotBlank(message = "Alamat tidak boleh kosong")
    @Size(max = 255, message = "Alamat tidak boleh lebih dari 255 karakter")
    private String alamat;

    @NotBlank(message = "Nomor telepon tidak boleh kosong")
    @Pattern(regexp = "\\d{10,15}", message = "Nomor telepon harus berupa angka dan memiliki panjang 10-15 karakter")
    private String nomorTelepon;

    public Pasien() {
        // Default constructor
    }

    public Pasien(String nama, String alamat, String nomorTelepon) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    @Override
    public String toString() {
        return "Pasien{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", nomorTelepon='" + nomorTelepon + '\'' +
                '}';
    }
}
