package com.DBTest.DBTest.controller;

import com.DBTest.DBTest.entity.Record;
import com.DBTest.DBTest.service.CoalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/daily_coal_records")
public class DailyCoalController {

    private final CoalRecordService coalRecordService;

    @Autowired
    public DailyCoalController(CoalRecordService coalRecordService) {
        this.coalRecordService = coalRecordService;
    }

    @GetMapping
    public List<Record> getAllRecords(@RequestParam(defaultValue = "postgres") String dbType, @RequestParam int page) {
        return coalRecordService.getAllRecords(dbType, page);
    }

    @GetMapping("/by_date")
    public List<Record> getRecordsByDateRange(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate == null && endDate == null) {
            throw new IllegalArgumentException("At least one date parameter must be provided");
        }
        if (endDate == null) {
            endDate = startDate;
        }
        return coalRecordService.getRecordsByDateRange(startDate, endDate);
    }

    @PostMapping
    public Record addRecord(@RequestBody Record record) {
        record.setDate(LocalDate.now());
        return coalRecordService.saveRecord(record);
    }

    @PutMapping("/{id}")
    public Record updateRecord(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return coalRecordService.updateRecord(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        coalRecordService.deleteRecord(id);
    }
}