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
    public List<MongoRecord> getAllRecords(@RequestParam int page, @RequestParam(required = false) String stateName, @RequestParam String queryId) {
        if (stateName != null) {
            return mongoRecordService.getRecordsByStateName(page, stateName, queryId);
        }
        return mongoRecordService.getAllRecords(page, queryId);
    }

    @PostMapping
    public MongoRecord addRecord(@RequestBody MongoRecord record, @RequestParam String queryId) {
        record.setDate(LocalDate.now());
        return mongoRecordService.saveRecord(record, queryId);
    }

    @PutMapping("/{id}")
    public MongoRecord updateRecord(@PathVariable String id, @RequestBody Map<String, Object> updates, @RequestParam String queryId) {
        return mongoRecordService.updateRecord(id, updates, queryId);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable String id, @RequestParam String queryId) {
        mongoRecordService.deleteRecord(id, queryId);
    }
}