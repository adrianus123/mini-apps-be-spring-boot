package com.tujuhsembilan.miniappsspringboot.template.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponse {
    private String qrcode;
    private String nama;
    private Double wallet;
}
