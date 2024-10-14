package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.Record;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;

public interface CoalRecordService {
    List<Record> getAllRecords(String dbType, int page);
    List<Record> getRecordsByDateRange(LocalDate startDate, LocalDate endDate);
    Record saveRecord(Record record);
    Record updateRecord(Long id, Map<String, Object> updates);
    void deleteRecord(Long id);
}