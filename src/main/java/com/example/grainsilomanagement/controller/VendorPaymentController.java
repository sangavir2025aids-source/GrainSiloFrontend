package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.VendorPayment;
import com.example.grainsilomanagement.service.VendorPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-payments")
@CrossOrigin
public class VendorPaymentController {

    private final VendorPaymentService service;

    public VendorPaymentController(VendorPaymentService service) {
        this.service = service;
    }

    @PostMapping
    public VendorPayment createPayment(@RequestBody VendorPayment payment) {
        return service.createPayment(payment);
    }

    @GetMapping
    public List<VendorPayment> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public VendorPayment getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
        return "Vendor payment deleted successfully";
    }
}