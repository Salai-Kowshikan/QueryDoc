package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.MongoRecord;
import com.DBTest.DBTest.repository.mongo.MongoRecordRepository;
import com.DBTest.DBTest.util.CsvLogger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class MongoRecordServiceImpl implements MongoRecordService {

    private final MongoRecordRepository mongoRecordRepository;

    @Autowired
    public MongoRecordServiceImpl(MongoRecordRepository mongoRecordRepository) {
        this.mongoRecordRepository = mongoRecordRepository;
    }

    @Override
    public List<MongoRecord> getAllRecords(int page, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> records = mongoRecordRepository.findAllWithoutId(PageRequest.of(page, 10000)).getContent();
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "mongo", queryId);
        return records;
    }

    @Override
    public List<MongoRecord> getRecordsByStateName(int page, String stateName, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> records = mongoRecordRepository.findByStateName(stateName, PageRequest.of(page, 10000)).getContent();
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "mongo", queryId);
        return records;
    }

    @Override
    public MongoRecord saveRecord(MongoRecord record, String queryId) {
        long startTime = System.currentTimeMillis();
        record.setDate(LocalDate.now());
        MongoRecord savedRecord = mongoRecordRepository.save(record);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Create", 1, endTime - startTime, "mongo", queryId);
        return savedRecord;
    }

    @Override
    @Transactional
    public MongoRecord updateRecord(String id, Map<String, Object> updates, String queryId) {
        long startTime = System.currentTimeMillis();
        MongoRecord record = mongoRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(record);
        updates.forEach((key, value) -> {
            if (beanWrapper.isWritableProperty(key)) {
                beanWrapper.setPropertyValue(key, value);
            }
        });

        MongoRecord updatedRecord = mongoRecordRepository.save(record);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", 1, endTime - startTime, "mongo", queryId);
        return updatedRecord;
    }

    @Override
    @Transactional
    public void deleteRecord(String id, String queryId) {
        long startTime = System.currentTimeMillis();
        if (!mongoRecordRepository.existsById(id)) {
            throw new IllegalArgumentException("Record not found");
        }
        mongoRecordRepository.deleteById(id);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Delete", 1, endTime - startTime, "mongo", queryId);
    }
}