package com.example.medicalcheckup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaPaket;
    private String jenisPemeriksaan;
    private double harga;

    // Default constructor
    public Paket() {}

    // Parameterized constructor
    public Paket(String namaPaket, String jenisPemeriksaan, double harga) {
        this.namaPaket = namaPaket;
        this.jenisPemeriksaan = jenisPemeriksaan;
        this.harga = harga;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getJenisPemeriksaan() {
        return jenisPemeriksaan;
    }

    public void setJenisPemeriksaan(String jenisPemeriksaan) {
        this.jenisPemeriksaan = jenisPemeriksaan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
