package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.VendorBill;
import com.example.grainsilomanagement.repository.VendorBillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorBillService {

    private final VendorBillRepository repository;

    public VendorBillService(VendorBillRepository repository) {
        this.repository = repository;
    }

    public VendorBill createVendorBill(VendorBill vendorBill) {

        vendorBill.setBillDate(LocalDateTime.now());

        if (vendorBill.getStatus() == null ||
                vendorBill.getStatus().isBlank()) {
            vendorBill.setStatus("UNPAID");
        }

        return repository.save(vendorBill);
    }

    public List<VendorBill> getAllVendorBills() {
        return repository.findAll();
    }

    public VendorBill getVendorBillById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteVendorBill(Long id) {
        repository.deleteById(id);
    }
}