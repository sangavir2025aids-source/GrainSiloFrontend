package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.AnalyticAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticAccountRepository extends JpaRepository<AnalyticAccount, Long> {
}