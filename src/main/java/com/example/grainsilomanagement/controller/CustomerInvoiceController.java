package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.CustomerInvoice;
import com.example.grainsilomanagement.service.CustomerInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-invoices")
@CrossOrigin
public class CustomerInvoiceController {

    private final CustomerInvoiceService service;

    public CustomerInvoiceController(CustomerInvoiceService service) {
        this.service = service;
    }

    // Create customer invoice
    @PostMapping
    public CustomerInvoice createInvoice(
            @RequestBody CustomerInvoice invoice) {

        return service.createInvoice(invoice);
    }

    // Get all invoices
    @GetMapping
    public List<CustomerInvoice> getAllInvoices() {
        return service.getAllInvoices();
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public CustomerInvoice getInvoiceById(
            @PathVariable Long id) {

        return service.getInvoiceById(id);
    }

    // Delete invoice
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {

        service.deleteInvoice(id);

        return "Customer invoice deleted successfully";
    }
}