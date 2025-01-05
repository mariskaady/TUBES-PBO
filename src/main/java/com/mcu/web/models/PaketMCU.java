package com.mcu.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "paket_mcu") // Memberikan nama tabel khusus di database
public class PaketMCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(max = 100, message = "Nama tidak boleh lebih dari 100 karakter")
    private String nama;

    @NotBlank(message = "Tipe tidak boleh kosong")
    @Size(max = 50, message = "Tipe tidak boleh lebih dari 50 karakter")
    private String tipe; // Jenis pemeriksaan

    @NotBlank(message = "Kategori tidak boleh kosong")
    @Size(max = 50, message = "Kategori tidak boleh lebih dari 50 karakter")
    private String kategori; // Tambahan kategori untuk query

    @NotNull(message = "Harga tidak boleh kosong")
    @Positive(message = "Harga harus bernilai positif")
    private double harga;

    // Default constructor
    public PaketMCU() {}

    // Constructor dengan parameter
    public PaketMCU(String nama, String tipe, String kategori, double harga) {
        this.nama = nama;
        this.tipe = tipe;
        this.kategori = kategori;
        this.harga = harga;
    }

    // Getter dan Setter
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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Override toString untuk debugging
    @Override
    public String toString() {
        return "PaketMCU{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", tipe='" + tipe + '\'' +
                ", kategori='" + kategori + '\'' +
                ", harga=" + harga +
                '}';
    }
}
