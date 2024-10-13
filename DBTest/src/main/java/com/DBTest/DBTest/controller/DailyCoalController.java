package com.DBTest.DBTest.controller;

import com.DBTest.DBTest.entity.Record;
import com.DBTest.DBTest.service.CoalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/daily_coal_records")
public class DailyCoalController {

    private final CoalRecordService coalRecordService;

    @Autowired
    public DailyCoalController(CoalRecordService coalRecordService) {
        this.coalRecordService = coalRecordService;
    }

    @GetMapping
    public List<Record> getAllRecords(@RequestParam(defaultValue = "0") int page) {
        return coalRecordService.getAllRecords(page);
    }
}