package com.tujuhsembilan.miniappsspringboot.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.miniappsspringboot.template.dto.CustomerResponse;
import com.tujuhsembilan.miniappsspringboot.template.dto.PaymentRequest;
import com.tujuhsembilan.miniappsspringboot.template.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }

    @GetMapping("/detail")
    public ResponseEntity<CustomerResponse> getDetailCustomer(@RequestParam String qrCode) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getDetailCustomer(qrCode));
    }

    @PutMapping("/payment")
    public ResponseEntity<?> customerPayment(@RequestBody PaymentRequest param) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerWallet(param));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
