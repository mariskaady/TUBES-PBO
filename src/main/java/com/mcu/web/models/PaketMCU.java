package com.mcu.web.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PaketMCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    @Column(columnDefinition="TEXT")
    private String deskripsi;
    private String jenis;
    private String kategori;
    private Integer harga;

    @OneToMany(mappedBy = "paketMCU")
    private List<PendaftaranMCU> pendaftaranMCUList;
}
