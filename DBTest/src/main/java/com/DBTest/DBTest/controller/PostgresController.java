package com.DBTest.DBTest.controller;

import com.DBTest.DBTest.entity.PostgresRecord;
import com.DBTest.DBTest.service.MongoRecordService;
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
    private final MongoRecordService mongoRecordService;

    @Autowired
    public PostgresController(PostgresRecordService postgresRecordService, MongoRecordService mongoRecordService) {
        this.postgresRecordService = postgresRecordService;
        this.mongoRecordService = mongoRecordService;
    }

    @GetMapping
    public List<PostgresRecord> getAllRecords(@RequestParam int page, @RequestParam(required = false) String stateName, @RequestParam String queryId) {
        if (stateName != null) {
            return postgresRecordService.getRecordsByStateName(page, stateName, queryId);
        }
        return postgresRecordService.getAllRecords(page, queryId);
    }

    @PostMapping
    public PostgresRecord addRecord(@org.jetbrains.annotations.NotNull @RequestBody PostgresRecord record, @RequestParam String queryId) {
        record.setDate(LocalDate.now());
        return postgresRecordService.saveRecord(record, queryId);
    }

    @PutMapping("/{id}")
    public PostgresRecord updateRecord(@PathVariable Long id, @RequestBody Map<String, Object> updates, @RequestParam String queryId) {
        return postgresRecordService.updateRecord(id, updates, queryId);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id, @RequestParam String queryId) {
        postgresRecordService.deleteRecord(id, queryId);
    }
}