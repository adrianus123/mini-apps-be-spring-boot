package com.tujuhsembilan.miniappsspringboot.template.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TransaksiKey implements Serializable {
    @Column(name = "QRCode")
    private String qrCode;

    @Column(name = "RFID")
    private String rfid;
}
