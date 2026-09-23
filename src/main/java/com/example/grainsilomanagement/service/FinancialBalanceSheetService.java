package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Account;
import com.example.grainsilomanagement.entity.JournalEntry;
import com.example.grainsilomanagement.repository.AccountRepository;
import com.example.grainsilomanagement.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FinancialBalanceSheetService {

    private final AccountRepository accountRepository;
    private final JournalEntryRepository journalEntryRepository;

    public FinancialBalanceSheetService(
            AccountRepository accountRepository,
            JournalEntryRepository journalEntryRepository) {

        this.accountRepository = accountRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    public Map<String, Object> getBalanceSheetReport() {

        List<Account> accounts = accountRepository.findAll();
        List<JournalEntry> entries = journalEntryRepository.findAll();

        double totalAssets = 0.0;
        double totalLiabilities = 0.0;

        for (Account account : accounts) {

            double balance = 0.0;

            for (JournalEntry entry : entries) {

                if (!account.getId().equals(entry.getAccountId())) {
                    continue;
                }

                if ("DEBIT".equalsIgnoreCase(entry.getEntryType())) {
                    balance += entry.getAmount();
                } else if ("CREDIT".equalsIgnoreCase(entry.getEntryType())) {
                    balance -= entry.getAmount();
                }
            }

            if ("ASSET".equalsIgnoreCase(account.getAccountType())) {
                totalAssets += balance;
            }

            if ("LIABILITY".equalsIgnoreCase(account.getAccountType())) {
                totalLiabilities += balance;
            }
        }

        Map<String, Object> report = new LinkedHashMap<>();

        report.put("totalAssets", totalAssets);
        report.put("totalLiabilities", totalLiabilities);

        return report;
    }
}