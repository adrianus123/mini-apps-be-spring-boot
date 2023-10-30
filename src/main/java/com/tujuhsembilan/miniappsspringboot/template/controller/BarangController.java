package com.tujuhsembilan.miniappsspringboot.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.miniappsspringboot.template.dto.BarangResponse;
import com.tujuhsembilan.miniappsspringboot.template.service.BarangService;

@RestController
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    private BarangService barangService;

    @GetMapping("/list")
    public ResponseEntity<List<BarangResponse>> getAllBarangs() {
        return ResponseEntity.status(HttpStatus.OK).body(barangService.getAllBarangs());
    }

    @GetMapping("/detail")
    public ResponseEntity<BarangResponse> getDetailBarang(@RequestParam String rfid) {
        return ResponseEntity.status(HttpStatus.OK).body(barangService.getDetailBarang(rfid));
    }
}
