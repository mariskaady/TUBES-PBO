package com.mcu.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pasien extends User{

    private String alamat;
    private boolean isPasien = false;

    @OneToMany(mappedBy = "pasien")
    private List<PendaftaranMCU> pendaftaranMCUList;
}
