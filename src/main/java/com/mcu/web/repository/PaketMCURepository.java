package com.mcu.web.repository;

import com.mcu.web.models.PaketMCU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaketMCURepository extends JpaRepository<PaketMCU, Long> {
    List<PaketMCU> findByJenis(String jenis);

    PaketMCU findByNama(String nama);

    @Query("SELECT MIN(p.harga) FROM PaketMCU p WHERE p.jenis = :jenis")
    Integer findCheapestPriceByJenis(String jenis);
}
