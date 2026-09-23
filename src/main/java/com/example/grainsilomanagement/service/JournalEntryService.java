package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.JournalEntry;
import com.example.grainsilomanagement.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    private final JournalEntryRepository repository;

    public JournalEntryService(JournalEntryRepository repository) {
        this.repository = repository;
    }

    public JournalEntry createEntry(JournalEntry entry) {
        return repository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return repository.findAll();
    }

    public JournalEntry getEntryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }
}