package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.VendorPayment;
import com.example.grainsilomanagement.repository.VendorPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorPaymentService {

    private final VendorPaymentRepository repository;

    public VendorPaymentService(VendorPaymentRepository repository) {
        this.repository = repository;
    }

    public VendorPayment createPayment(VendorPayment payment) {

        payment.setPaymentDate(LocalDateTime.now());

        if (payment.getStatus() == null ||
                payment.getStatus().isBlank()) {
            payment.setStatus("COMPLETED");
        }

        return repository.save(payment);
    }

    public List<VendorPayment> getAllPayments() {
        return repository.findAll();
    }

    public VendorPayment getPaymentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}