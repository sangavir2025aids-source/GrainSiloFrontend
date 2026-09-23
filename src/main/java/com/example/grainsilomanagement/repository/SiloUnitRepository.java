package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.SiloUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiloUnitRepository extends JpaRepository<SiloUnit, Long> {
}