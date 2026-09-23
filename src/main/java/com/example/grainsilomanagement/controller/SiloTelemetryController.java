package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.SiloTelemetry;
import com.example.grainsilomanagement.service.SiloTelemetryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/silos/telemetry")
@CrossOrigin
public class SiloTelemetryController {

    private final SiloTelemetryService service;

    public SiloTelemetryController(SiloTelemetryService service) {
        this.service = service;
    }

    // Add telemetry reading
    @PostMapping
    public SiloTelemetry recordTelemetry(
            @RequestBody SiloTelemetry telemetry) {

        return service.recordTelemetry(telemetry);
    }

    // Get all telemetry records
    @GetMapping
    public List<SiloTelemetry> getAllTelemetry() {

        return service.getAllTelemetry();
    }

    // Get telemetry by ID
    @GetMapping("/{id}")
    public SiloTelemetry getTelemetryById(
            @PathVariable Long id) {

        return service.getTelemetryById(id);
    }
}