package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Vendor;
import com.example.grainsilomanagement.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository repository;

    public VendorService(VendorRepository repository) {
        this.repository = repository;
    }

    // Create vendor
    public Vendor createVendor(Vendor vendor) {
        return repository.save(vendor);
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return repository.findAll();
    }

    // Get vendor by ID
    public Vendor getVendorById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete vendor
    public void deleteVendor(Long id) {
        repository.deleteById(id);
    }
}