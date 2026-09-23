package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Budget;
import com.example.grainsilomanagement.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository repository;

    public BudgetService(BudgetRepository repository) {
        this.repository = repository;
    }

    public Budget createBudget(Budget budget) {

        if (budget.getActualAmount() == null) {
            budget.setActualAmount(0.0);
        }

        return repository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return repository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteBudget(Long id) {
        repository.deleteById(id);
    }
}