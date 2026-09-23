package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.service.FinancialReportService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class FinancialReportController {

    private final FinancialReportService service;

    public FinancialReportController(FinancialReportService service) {
        this.service = service;
    }

    @GetMapping("/profit-loss")
    public Map<String, Object> getProfitAndLossReport() {
        return service.getProfitAndLossReport();
    }
}