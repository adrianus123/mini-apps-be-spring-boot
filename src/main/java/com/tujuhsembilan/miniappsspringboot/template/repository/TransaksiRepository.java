package com.tujuhsembilan.miniappsspringboot.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.miniappsspringboot.template.model.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
}
