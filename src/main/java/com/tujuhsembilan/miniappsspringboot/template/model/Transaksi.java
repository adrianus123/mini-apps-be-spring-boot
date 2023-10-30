package com.tujuhsembilan.miniappsspringboot.template.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaksi_id")
    private Integer transaksiId;

    @Column(name = "Harga")
    private Double harga;

    @Column(name = "Jumlah")
    private Integer jumlah;

    @CreatedDate
    @Column(name = "tanggal")
    private LocalDateTime tanggal;

    @ManyToOne
    // @MapsId("qrCode")
    @JoinColumn(name = "QRCode")
    private Customer customer;

    @ManyToOne
    // @MapsId("rfid")
    @JoinColumn(name = "RFID")
    private Barang barang;
}
