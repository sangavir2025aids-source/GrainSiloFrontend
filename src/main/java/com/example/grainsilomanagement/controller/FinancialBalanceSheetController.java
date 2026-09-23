package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.service.FinancialBalanceSheetService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class FinancialBalanceSheetController {

    private final FinancialBalanceSheetService service;

    public FinancialBalanceSheetController(FinancialBalanceSheetService service) {
        this.service = service;
    }

    @GetMapping("/balance-sheet")
    public Map<String, Object> getBalanceSheetReport() {
        return service.getBalanceSheetReport();
    }
}