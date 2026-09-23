package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.JournalEntry;
import com.example.grainsilomanagement.service.JournalEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal-entries")
@CrossOrigin
public class JournalEntryController {

    private final JournalEntryService service;

    public JournalEntryController(JournalEntryService service) {
        this.service = service;
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        return service.createEntry(entry);
    }

    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return service.getAllEntries();
    }

    @GetMapping("/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return service.getEntryById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEntry(@PathVariable Long id) {
        service.deleteEntry(id);
        return "Journal entry deleted successfully";
    }
}