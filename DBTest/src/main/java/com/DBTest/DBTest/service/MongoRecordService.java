package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.MongoRecord;
import java.util.List;
import java.util.Map;

public interface MongoRecordService {
    List<MongoRecord> getAllRecords(int page,String queryId);
    List<MongoRecord> getRecordsByStateName(int page, String stateName, String queryId);
    MongoRecord saveRecord(MongoRecord record,String queryId);
    MongoRecord updateRecord(String id, Map<String, Object> updates,String queryId);
    void deleteRecord(String id,String queryId);
    List<MongoRecord> saveRecords(List<MongoRecord> records, String queryId);
    void deleteLastRecords(int count, String queryId);
    List<MongoRecord> updateRecords(List<Map<String, Object>> updates, String queryId);
    List<MongoRecord> updateLastRecords(Map<String, Object> updates, String queryId);
    List<MongoRecord> getLastRecords(int count, String queryId);
    List<MongoRecord> searchAndUpdateRecords(Map<String, Object> findCriteria, Map<String, Object> updateFields, String queryId);
}