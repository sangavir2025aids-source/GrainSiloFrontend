package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.SiloTelemetry;
import com.example.grainsilomanagement.repository.SiloTelemetryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SiloTelemetryService {

    private final SiloTelemetryRepository repository;

    public SiloTelemetryService(SiloTelemetryRepository repository) {
        this.repository = repository;
    }

    // Record telemetry and automatically activate fan
    public SiloTelemetry recordTelemetry(SiloTelemetry telemetry) {

        // Moisture threshold = 15%
        if (telemetry.getMoisture() != null &&
                telemetry.getMoisture() > 15.0) {

            telemetry.setFanActivated(true);

        } else {

            telemetry.setFanActivated(false);
        }

        // Set current date and time
        telemetry.setRecordedAt(LocalDateTime.now());

        return repository.save(telemetry);
    }

    // Get all telemetry records
    public List<SiloTelemetry> getAllTelemetry() {
        return repository.findAll();
    }

    // Get telemetry by ID
    public SiloTelemetry getTelemetryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Telemetry not found with id: " + id));
    }
}