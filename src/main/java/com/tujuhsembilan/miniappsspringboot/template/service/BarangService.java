package com.tujuhsembilan.miniappsspringboot.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.miniappsspringboot.template.dto.BarangResponse;
import com.tujuhsembilan.miniappsspringboot.template.model.Barang;
import com.tujuhsembilan.miniappsspringboot.template.repository.BarangRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BarangService {
    @Autowired
    private BarangRepository barangRepository;

    public List<BarangResponse> getAllBarangs() {
        List<BarangResponse> barangResponses = new ArrayList<>();

        List<Barang> barangs = barangRepository.findAll();
        for (Barang barang : barangs) {
            BarangResponse response = new BarangResponse();
            response.setRfid(barang.getRfid());
            response.setNamaBarang(barang.getNamaBarang());
            response.setHarga(barang.getHarga());

            barangResponses.add(response);
        }

        return barangResponses;
    }

    public BarangResponse getDetailBarang(String rfid) {
        try {
            Barang barang = barangRepository.findByRfid(rfid);

            if (barang == null) {
                throw new EntityNotFoundException("Barang not found");
            }

            BarangResponse response = new BarangResponse();
            response.setRfid(barang.getRfid());
            response.setNamaBarang(barang.getNamaBarang());
            response.setHarga(barang.getHarga());

            return response;
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
