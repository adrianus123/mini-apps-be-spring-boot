package com.tujuhsembilan.miniappsspringboot.template.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransaksiDto {
    private Integer transaksiId;
    private String customer;
    private String barang;
    private Double harga;
    private Integer jumlah;
    private String tanggal;
}
