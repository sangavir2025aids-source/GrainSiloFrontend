package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.Budget;
import com.example.grainsilomanagement.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return service.createBudget(budget);
    }

    @GetMapping
    public List<Budget> getAllBudgets() {
        return service.getAllBudgets();
    }

    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return service.getBudgetById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBudget(@PathVariable Long id) {
        service.deleteBudget(id);
        return "Budget deleted successfully";
    }
}