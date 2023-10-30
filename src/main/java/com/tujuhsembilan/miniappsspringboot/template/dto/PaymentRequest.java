package com.tujuhsembilan.miniappsspringboot.template.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private Double amount;
    private String qrcode;
}
