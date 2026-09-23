package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.SiloUnit;
import com.example.grainsilomanagement.repository.SiloUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiloUnitService {

    private final SiloUnitRepository repository;

    public SiloUnitService(SiloUnitRepository repository) {
        this.repository = repository;
    }

    // Create a new silo
    public SiloUnit createSilo(SiloUnit silo) {
        return repository.save(silo);
    }

    // Get all silos
    public List<SiloUnit> getAllSilos() {
        return repository.findAll();
    }

    // Get silo by ID
    public SiloUnit getSiloById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete silo
    public void deleteSilo(Long id) {
        repository.deleteById(id);
    }
}