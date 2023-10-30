package com.tujuhsembilan.miniappsspringboot.template.dto;

import java.util.List;

import lombok.Data;

@Data
public class TransaksiResponse {
    private List<TransaksiDto> response;
    private String source;
}
