package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.PostgresRecord;
import java.util.List;
import java.util.Map;

public interface PostgresRecordService {
    List<PostgresRecord> getAllRecords(int page);
    List<PostgresRecord> getRecordsByStateName(int page, String stateName);
    PostgresRecord saveRecord(PostgresRecord record);
    PostgresRecord updateRecord(Long id, Map<String, Object> updates);
    void deleteRecord(Long id);
}