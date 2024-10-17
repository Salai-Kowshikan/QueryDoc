package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.PostgresRecord;
import java.util.List;
import java.util.Map;

public interface PostgresRecordService {
    List<PostgresRecord> getAllRecords(int page,String queryId);
    List<PostgresRecord> getRecordsByStateName(int page, String stateName, String queryId);
    PostgresRecord saveRecord(PostgresRecord record, String queryId);
    PostgresRecord updateRecord(Long id, Map<String, Object> updates, String queryId);
    void deleteRecord(Long id,String queryId);
    List<PostgresRecord> getLastRecords(int count, String queryId);
    List<PostgresRecord> updateLastRecords(Map<String, Object> updates, String queryId);
    void deleteLastRecords(int count, String queryId);
    List<PostgresRecord> insertRecords(List<PostgresRecord> records, String queryId);
}