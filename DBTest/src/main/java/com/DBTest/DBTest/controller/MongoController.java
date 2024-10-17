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
    public List<MongoRecord> addRecords(@RequestBody List<MongoRecord> records, @RequestParam String queryId) {
        records.forEach(record -> record.setDate(LocalDate.now()));
        return mongoRecordService.saveRecords(records, queryId);
    }

    @PutMapping("/{id}")
    public MongoRecord updateRecord(@PathVariable String id, @RequestBody Map<String, Object> updates, @RequestParam String queryId) {
        return mongoRecordService.updateRecord(id, updates, queryId);
    }

    @DeleteMapping("/bulk")
    public void deleteLastRecords(@RequestParam int count, @RequestParam String queryId) {
        mongoRecordService.deleteLastRecords(count, queryId);
    }

    @PutMapping("/bulk")
    public List<MongoRecord> updateRecords(@RequestBody List<Map<String, Object>> updates, @RequestParam String queryId) {
        return mongoRecordService.updateRecords(updates, queryId);
    }

    @PutMapping("/bulk/last")
    public List<MongoRecord> updateLastRecords(@RequestBody Map<String, Object> updates, @RequestParam String queryId) {
        return mongoRecordService.updateLastRecords(updates, queryId);
    }

    @GetMapping("/bulk/last")
    public List<MongoRecord> getLastRecords(@RequestParam int count, @RequestParam String queryId) {
        return mongoRecordService.getLastRecords(count, queryId);
    }

    @PutMapping("/bulk/search-update")
    public List<MongoRecord> searchAndUpdateRecords(@RequestBody Map<String, Map<String, Object>> request, @RequestParam String queryId) {
        Map<String, Object> findCriteria = request.get("find");
        Map<String, Object> updateFields = request.get("update");
        return mongoRecordService.searchAndUpdateRecords(findCriteria, updateFields, queryId);
    }
}