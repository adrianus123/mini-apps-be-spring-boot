package com.tujuhsembilan.miniappsspringboot.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.miniappsspringboot.template.dto.GeneralResponse;
import com.tujuhsembilan.miniappsspringboot.template.dto.TransaksiResponse;
import com.tujuhsembilan.miniappsspringboot.template.dto.TransaksiRequest;
import com.tujuhsembilan.miniappsspringboot.template.dto.TransaksiDto;
import com.tujuhsembilan.miniappsspringboot.template.model.Barang;
import com.tujuhsembilan.miniappsspringboot.template.model.Customer;
import com.tujuhsembilan.miniappsspringboot.template.model.Transaksi;
import com.tujuhsembilan.miniappsspringboot.template.repository.BarangRepository;
import com.tujuhsembilan.miniappsspringboot.template.repository.CustomerRepository;
import com.tujuhsembilan.miniappsspringboot.template.repository.TransaksiRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransaksiService {
    private static final String REDIS_KEY = "transactionsPostgres";

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public TransaksiResponse getAllTransaksi() {
        List<TransaksiDto> transaksiResponse = new ArrayList<>();
        TransaksiResponse data = new TransaksiResponse();

        List<TransaksiDto> cacheTransaksi = getAllTransaksiFromRedis();
        if (!cacheTransaksi.isEmpty()) {
            data.setResponse(cacheTransaksi);
            data.setSource("Get data from Redis");
        } else {
            List<Transaksi> transaksi = transaksiRepository.findAll();
            for (Transaksi tr : transaksi) {
                TransaksiDto response = new TransaksiDto();
                response.setTransaksiId(tr.getTransaksiId());
                response.setBarang(tr.getBarang().getNamaBarang());
                response.setCustomer(tr.getCustomer().getNama());
                response.setHarga(tr.getHarga());
                response.setJumlah(tr.getJumlah());
                response.setTanggal(tr.getTanggal().toString());
                transaksiResponse.add(response);
                storeDataTransactionToRedis(response.getTransaksiId().toString(), response);
            }

            data.setResponse(transaksiResponse);
            data.setSource("Get data from API");
        }

        return data;
    }

    public GeneralResponse addTransaction(TransaksiRequest request) {
        try {
            Barang barang = barangRepository.findByRfid(request.getRfid());
            Customer customer = customerRepository.findByQrCode(request.getQrcode());

            if (barang == null || customer == null) {
                throw new EntityNotFoundException("Transaction Failed");
            }

            Transaksi transaksi = new Transaksi();
            transaksi.setBarang(barang);
            transaksi.setCustomer(customer);
            transaksi.setHarga(request.getHarga());
            transaksi.setJumlah(request.getJumlah());
            transaksi.setTanggal(request.getTanggal());
            transaksiRepository.save(transaksi);

            deleteTransactionRedis();

            GeneralResponse response = new GeneralResponse();
            response.setMessage("Transaksi berhasil ditambahkan");

            return response;
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public List<TransaksiDto> getAllTransaksiFromRedis() {
        return redisTemplate.opsForHash().values(REDIS_KEY);
    }

    public void storeDataTransactionToRedis(String hashId, TransaksiDto transaction) {
        redisTemplate.opsForHash().put(REDIS_KEY, hashId, transaction);
    }

    public void deleteTransactionRedis() {
        redisTemplate.delete(REDIS_KEY);
    }
}
