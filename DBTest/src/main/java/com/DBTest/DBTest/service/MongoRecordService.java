package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.MongoRecord;
import java.util.List;
import java.util.Map;

public interface MongoRecordService {
    List<MongoRecord> getAllRecords(int page);
    List<MongoRecord> getRecordsByStateName(int page, String stateName);
    MongoRecord saveRecord(MongoRecord record);
    MongoRecord updateRecord(String id, Map<String, Object> updates);
    void deleteRecord(String id);
}