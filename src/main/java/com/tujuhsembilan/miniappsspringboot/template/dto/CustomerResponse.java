package com.tujuhsembilan.miniappsspringboot.template.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerResponse {
    private String qrcode;
    private String nama;
    private Double wallet;
}
