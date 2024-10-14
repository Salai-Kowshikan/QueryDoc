package com.DBTest.DBTest.controller;

import com.DBTest.DBTest.entity.PostgresRecord;
import com.DBTest.DBTest.service.PostgresRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/postgres/daily_coal_records")
public class PostgresController {

    private final PostgresRecordService postgresRecordService;

    @Autowired
    public PostgresController(PostgresRecordService postgresRecordService) {
        this.postgresRecordService = postgresRecordService;
    }

    @GetMapping
    public List<PostgresRecord> getAllRecords(@RequestParam int page, @RequestParam(required = false) String stateName) {
        if (stateName != null) {
            return postgresRecordService.getRecordsByStateName(page, stateName);
        }
        return postgresRecordService.getAllRecords(page);
    }

    @PostMapping
    public PostgresRecord addRecord(@RequestBody PostgresRecord record) {
        record.setDate(LocalDate.now());
        return postgresRecordService.saveRecord(record);
    }

    @PutMapping("/{id}")
    public PostgresRecord updateRecord(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return postgresRecordService.updateRecord(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        postgresRecordService.deleteRecord(id);
    }
}