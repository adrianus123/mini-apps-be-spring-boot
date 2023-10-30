package com.tujuhsembilan.miniappsspringboot.template.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransaksiRequest {
    private String qrcode;
    private String rfid;
    private Double harga;
    private Integer jumlah;
    private LocalDateTime tanggal;
}
