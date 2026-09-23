package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.VendorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorPaymentRepository extends JpaRepository<VendorPayment, Long> {
}