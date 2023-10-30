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
@Table(name = "Customer", schema = "public")
public class Customer {
    @Id
    @Column(name = "QRCode")
    private String qrCode;

    @Column(name = "Nama")
    private String nama;

    @Column(name = "Wallet")
    private Double wallet;

    @OneToMany(mappedBy = "customer")
    private List<Transaksi> transaksis;
}
