package com.tujuhsembilan.miniappsspringboot.template.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Barang", schema = "public")
public class Barang {
    @Id
    @Column(name = "RFID")
    private String rfid;

    @Column(name = "Nama_Barang")
    private String namaBarang;

    @Column(name = "Harga")
    private Double harga;

    @OneToMany(mappedBy = "barang")
    private List<Transaksi> transaksis;
}
