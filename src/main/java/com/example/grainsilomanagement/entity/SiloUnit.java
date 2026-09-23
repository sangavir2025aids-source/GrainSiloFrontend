package com.example.grainsilomanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "silo_units")
public class SiloUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siloName;

    private String location;

    private Double capacity;

    private String grainType;

    private String status;

    // Default constructor
    public SiloUnit() {
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Getter and Setter for siloName
    public String getSiloName() {
        return siloName;
    }

    public void setSiloName(String siloName) {
        this.siloName = siloName;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for capacity
    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    // Getter and Setter for grainType
    public String getGrainType() {
        return grainType;
    }

    public void setGrainType(String grainType) {
        this.grainType = grainType;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}