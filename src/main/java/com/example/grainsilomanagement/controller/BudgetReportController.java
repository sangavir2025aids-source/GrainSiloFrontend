package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.service.BudgetReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class BudgetReportController {

    private final BudgetReportService service;

    public BudgetReportController(BudgetReportService service) {
        this.service = service;
    }

    @GetMapping("/budget")
    public List<Map<String, Object>> getBudgetReport() {
        return service.getBudgetReport();
    }
}