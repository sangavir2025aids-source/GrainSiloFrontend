package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice, Long> {
}