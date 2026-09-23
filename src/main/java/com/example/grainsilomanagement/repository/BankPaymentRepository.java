package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.BankPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankPaymentRepository extends JpaRepository<BankPayment, Long> {
}