package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.CustomerInvoice;
import com.example.grainsilomanagement.repository.CustomerInvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerInvoiceService {

    private final CustomerInvoiceRepository repository;

    public CustomerInvoiceService(CustomerInvoiceRepository repository) {
        this.repository = repository;
    }

    // Create customer invoice
    public CustomerInvoice createInvoice(CustomerInvoice invoice) {

        invoice.setInvoiceDate(LocalDateTime.now());

        if (invoice.getStatus() == null ||
                invoice.getStatus().isBlank()) {
            invoice.setStatus("UNPAID");
        }

        return repository.save(invoice);
    }

    // Get all invoices
    public List<CustomerInvoice> getAllInvoices() {
        return repository.findAll();
    }

    // Get invoice by ID
    public CustomerInvoice getInvoiceById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete invoice
    public void deleteInvoice(Long id) {
        repository.deleteById(id);
    }
}