package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.Vendor;
import com.example.grainsilomanagement.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }

    // Create vendor
    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return service.createVendor(vendor);
    }

    // Get all vendors
    @GetMapping
    public List<Vendor> getAllVendors() {
        return service.getAllVendors();
    }

    // Get vendor by ID
    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return service.getVendorById(id);
    }

    // Delete vendor
    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {
        service.deleteVendor(id);
        return "Vendor deleted successfully";
    }
}