package com.DBTest.DBTest.controller;

import com.DBTest.DBTest.entity.MongoRecord;
import com.DBTest.DBTest.service.MongoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mongo/daily_coal_records")
public class MongoController {

    private final MongoRecordService mongoRecordService;

    @Autowired
    public MongoController(MongoRecordService mongoRecordService) {
        this.mongoRecordService = mongoRecordService;
    }

    @GetMapping
    public List<MongoRecord> getAllRecords(@RequestParam int page, @RequestParam(required = false) String stateName) {
        if (stateName != null) {
            return mongoRecordService.getRecordsByStateName(page, stateName);
        }
        return mongoRecordService.getAllRecords(page);
    }

    @PostMapping
    public MongoRecord addRecord(@RequestBody MongoRecord record) {
        record.setDate(LocalDate.now());
        return mongoRecordService.saveRecord(record);
    }

    @PutMapping("/{id}")
    public MongoRecord updateRecord(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return mongoRecordService.updateRecord(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable String id) {
        mongoRecordService.deleteRecord(id);
    }
}