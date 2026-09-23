package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.BankPayment;
import com.example.grainsilomanagement.repository.BankPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankPaymentService {

    private final BankPaymentRepository repository;

    public BankPaymentService(BankPaymentRepository repository) {
        this.repository = repository;
    }

    public BankPayment createPayment(BankPayment payment) {
        payment.setPaymentDate(LocalDateTime.now());

        if (payment.getStatus() == null || payment.getStatus().isBlank()) {
            payment.setStatus("COMPLETED");
        }

        return repository.save(payment);
    }

    public List<BankPayment> getAllPayments() {
        return repository.findAll();
    }

    public BankPayment getPaymentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}