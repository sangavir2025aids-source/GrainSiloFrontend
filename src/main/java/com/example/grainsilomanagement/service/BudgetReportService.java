package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Budget;
import com.example.grainsilomanagement.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BudgetReportService {

    private final BudgetRepository budgetRepository;

    public BudgetReportService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<Map<String, Object>> getBudgetReport() {

        List<Budget> budgets = budgetRepository.findAll();

        List<Map<String, Object>> report = new ArrayList<>();

        for (Budget budget : budgets) {

            double budgetAmount = budget.getBudgetAmount() != null
                    ? budget.getBudgetAmount()
                    : 0.0;

            double actualAmount = budget.getActualAmount() != null
                    ? budget.getActualAmount()
                    : 0.0;

            double remainingAmount = budgetAmount - actualAmount;

            Map<String, Object> row = new LinkedHashMap<>();

            row.put("budgetId", budget.getId());
            row.put("analyticAccountId", budget.getAnalyticAccountId());
            row.put("accountId", budget.getAccountId());
            row.put("period", budget.getPeriod());
            row.put("budgetAmount", budgetAmount);
            row.put("actualAmount", actualAmount);
            row.put("remainingAmount", remainingAmount);
            row.put("description", budget.getDescription());

            report.add(row);
        }

        return report;
    }
}