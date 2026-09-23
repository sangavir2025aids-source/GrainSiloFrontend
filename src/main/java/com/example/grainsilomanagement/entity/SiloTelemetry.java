package com.example.grainsilomanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "silo_telemetry")
public class SiloTelemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long siloId;

    private Double moisture;

    private Double temperature;

    private Boolean fanActivated;

    private LocalDateTime recordedAt;

    // Default constructor
    public SiloTelemetry() {
    }

    // Constructor
    public SiloTelemetry(Long siloId,
                         Double moisture,
                         Double temperature,
                         Boolean fanActivated,
                         LocalDateTime recordedAt) {

        this.siloId = siloId;
        this.moisture = moisture;
        this.temperature = temperature;
        this.fanActivated = fanActivated;
        this.recordedAt = recordedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiloId() {
        return siloId;
    }

    public void setSiloId(Long siloId) {
        this.siloId = siloId;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getFanActivated() {
        return fanActivated;
    }

    public void setFanActivated(Boolean fanActivated) {
        this.fanActivated = fanActivated;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}