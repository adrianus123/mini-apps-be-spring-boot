package com.tujuhsembilan.miniappsspringboot.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tujuhsembilan.miniappsspringboot.template.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByQrCode(String qrCode);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE customer SET wallet = wallet - :payment WHERE qrcode = :userQR")
    void updateCustomerWallet(@Param("payment") Double payment, @Param("userQR") String userQR);
}
