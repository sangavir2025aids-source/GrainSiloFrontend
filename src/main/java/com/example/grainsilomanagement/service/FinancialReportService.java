package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Account;
import com.example.grainsilomanagement.entity.JournalEntry;
import com.example.grainsilomanagement.repository.AccountRepository;
import com.example.grainsilomanagement.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FinancialReportService {

    private final AccountRepository accountRepository;
    private final JournalEntryRepository journalEntryRepository;

    public FinancialReportService(AccountRepository accountRepository,
                                  JournalEntryRepository journalEntryRepository) {
        this.accountRepository = accountRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    public Map<String, Object> getProfitAndLossReport() {

        List<Account> accounts = accountRepository.findAll();
        List<JournalEntry> entries = journalEntryRepository.findAll();

        double totalIncome = 0.0;
        double totalExpense = 0.0;

        for (JournalEntry entry : entries) {

            Account account = accounts.stream()
                    .filter(a -> a.getId().equals(entry.getAccountId()))
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                continue;
            }

            if ("INCOME".equalsIgnoreCase(account.getAccountType())) {
                if ("CREDIT".equalsIgnoreCase(entry.getEntryType())) {
                    totalIncome += entry.getAmount();
                } else {
                    totalIncome -= entry.getAmount();
                }
            }

            if ("EXPENSE".equalsIgnoreCase(account.getAccountType())) {
                if ("DEBIT".equalsIgnoreCase(entry.getEntryType())) {
                    totalExpense += entry.getAmount();
                } else {
                    totalExpense -= entry.getAmount();
                }
            }
        }

        double netProfit = totalIncome - totalExpense;

        Map<String, Object> report = new LinkedHashMap<>();

        report.put("totalIncome", totalIncome);
        report.put("totalExpense", totalExpense);
        report.put("netProfit", netProfit);

        return report;
    }
}