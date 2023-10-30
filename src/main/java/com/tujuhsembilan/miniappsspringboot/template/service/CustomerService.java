package com.tujuhsembilan.miniappsspringboot.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tujuhsembilan.miniappsspringboot.template.ApiException;
import com.tujuhsembilan.miniappsspringboot.template.dto.CustomerResponse;
import com.tujuhsembilan.miniappsspringboot.template.dto.PaymentRequest;
import com.tujuhsembilan.miniappsspringboot.template.dto.PaymentResponse;
import com.tujuhsembilan.miniappsspringboot.template.model.Customer;
import com.tujuhsembilan.miniappsspringboot.template.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerResponse> getAllCustomers() {
        List<CustomerResponse> customerResponse = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            CustomerResponse response = new CustomerResponse();
            response.setQrcode(customer.getQrCode());
            response.setNama(customer.getNama());
            response.setWallet(customer.getWallet());

            customerResponse.add(response);
        }

        return customerResponse;
    }

    public CustomerResponse getDetailCustomer(String qrCode) {
        try {
            Customer customer = customerRepository.findByQrCode(qrCode);
            if (customer == null) {
                throw new ApiException("Customer not found");
            }

            CustomerResponse response = new CustomerResponse();
            response.setQrcode(customer.getQrCode());
            response.setNama(customer.getNama());
            response.setWallet(customer.getWallet());

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PaymentResponse updateCustomerWallet(PaymentRequest param) {
        try {
            Customer customer = customerRepository.findByQrCode(param.getQrcode());
            if (customer == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
            }

            if (customer.getWallet() == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your wallet is empty.");
            }

            if (customer.getWallet() - param.getAmount() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Your wallet is not enough. Please top up your wallet.");
            }

            customer.setWallet(customer.getWallet() - param.getAmount());
            customerRepository.save(customer);

            PaymentResponse response = new PaymentResponse();
            response.setQrcode(customer.getQrCode());
            response.setNama(customer.getNama());
            response.setWallet(customer.getWallet());

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
