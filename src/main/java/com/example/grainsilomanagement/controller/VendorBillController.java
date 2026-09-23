package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.VendorBill;
import com.example.grainsilomanagement.service.VendorBillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-bills")
@CrossOrigin
public class VendorBillController {

    private final VendorBillService service;

    public VendorBillController(VendorBillService service) {
        this.service = service;
    }

    @PostMapping
    public VendorBill createVendorBill(@RequestBody VendorBill vendorBill) {
        return service.createVendorBill(vendorBill);
    }

    @GetMapping
    public List<VendorBill> getAllVendorBills() {
        return service.getAllVendorBills();
    }

    @GetMapping("/{id}")
    public VendorBill getVendorBillById(@PathVariable Long id) {
        return service.getVendorBillById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVendorBill(@PathVariable Long id) {
        service.deleteVendorBill(id);
        return "Vendor bill deleted successfully";
    }
}