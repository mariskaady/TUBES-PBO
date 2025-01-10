package com.mcu.web.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Petugas extends User{
    private String jabatan;
}
