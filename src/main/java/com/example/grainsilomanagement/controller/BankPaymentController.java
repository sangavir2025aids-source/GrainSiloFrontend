package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.BankPayment;
import com.example.grainsilomanagement.service.BankPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-payments")
@CrossOrigin
public class BankPaymentController {

    private final BankPaymentService service;

    public BankPaymentController(BankPaymentService service) {
        this.service = service;
    }

    @PostMapping
    public BankPayment createPayment(@RequestBody BankPayment payment) {
        return service.createPayment(payment);
    }

    @GetMapping
    public List<BankPayment> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public BankPayment getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
        return "Bank payment deleted successfully";
    }
}