package com.tujuhsembilan.miniappsspringboot.template.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BarangResponse {
    private String rfid;
    private String namaBarang;
    private Double harga;
}
