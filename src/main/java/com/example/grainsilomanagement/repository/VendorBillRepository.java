package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.VendorBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorBillRepository extends JpaRepository<VendorBill, Long> {
}