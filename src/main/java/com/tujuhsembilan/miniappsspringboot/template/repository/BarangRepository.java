package com.tujuhsembilan.miniappsspringboot.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.miniappsspringboot.template.model.Barang;

@Repository
public interface BarangRepository extends JpaRepository<Barang, String> {
    Barang findByRfid(String rfid);
}
