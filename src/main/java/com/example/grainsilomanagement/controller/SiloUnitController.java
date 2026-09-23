package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.SiloUnit;
import com.example.grainsilomanagement.service.SiloUnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/silos/units")
@CrossOrigin
public class SiloUnitController {

    private final SiloUnitService service;

    public SiloUnitController(SiloUnitService service) {
        this.service = service;
    }

    // Create a new silo
    @PostMapping
    public SiloUnit createSilo(@RequestBody SiloUnit silo) {
        return service.createSilo(silo);
    }

    // Get all silos
    @GetMapping
    public List<SiloUnit> getAllSilos() {
        return service.getAllSilos();
    }

    // Get silo by ID
    @GetMapping("/{id}")
    public SiloUnit getSiloById(@PathVariable Long id) {
        return service.getSiloById(id);
    }

    // Delete silo
    @DeleteMapping("/{id}")
    public String deleteSilo(@PathVariable Long id) {
        service.deleteSilo(id);
        return "Silo deleted successfully";
    }
}