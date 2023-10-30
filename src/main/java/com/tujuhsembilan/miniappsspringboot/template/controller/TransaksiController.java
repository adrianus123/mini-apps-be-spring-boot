package com.tujuhsembilan.miniappsspringboot.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.miniappsspringboot.template.dto.GeneralResponse;
import com.tujuhsembilan.miniappsspringboot.template.dto.TransaksiResponse;
import com.tujuhsembilan.miniappsspringboot.template.dto.TransaksiRequest;
import com.tujuhsembilan.miniappsspringboot.template.service.TransaksiService;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    @Autowired
    private TransaksiService transaksiService;

    @GetMapping("/list")
    public ResponseEntity<TransaksiResponse> getAllTransaksi() {
        return ResponseEntity.status(HttpStatus.OK).body(transaksiService.getAllTransaksi());
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralResponse> addTransaction(@RequestBody TransaksiRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(transaksiService.addTransaction(request));
    }
}
