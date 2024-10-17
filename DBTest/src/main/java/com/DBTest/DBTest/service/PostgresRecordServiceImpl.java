package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.PostgresRecord;
import com.DBTest.DBTest.repository.Postgres.PostgresRepository;
import com.DBTest.DBTest.util.CsvLogger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostgresRecordServiceImpl implements PostgresRecordService {

    private final PostgresRepository postgresRepository;

    @Autowired
    public PostgresRecordServiceImpl(PostgresRepository postgresRepository) {
        this.postgresRepository = postgresRepository;
    }

    @Override
    public List<PostgresRecord> getAllRecords(int page, String queryId) {
        long startTime = System.currentTimeMillis();
        List<PostgresRecord> records = postgresRepository.findAll(PageRequest.of(page, 10000)).getContent();
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "postgres", queryId);
        return records;
    }

    @Override
    public List<PostgresRecord> getRecordsByStateName(int page, String stateName, String queryId) {
        long startTime = System.currentTimeMillis();
        List<PostgresRecord> records = postgresRepository.findByStateName(stateName, PageRequest.of(page, 10000)).getContent();
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "postgres", queryId);
        return records;
    }

    @Override
    public PostgresRecord saveRecord(PostgresRecord record, String queryId) {
        long startTime = System.currentTimeMillis();
        PostgresRecord savedRecord = postgresRepository.save(record);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Create", 1, endTime - startTime, "postgres", queryId);
        return savedRecord;
    }

    @Override
    @Transactional
    public PostgresRecord updateRecord(Long id, Map<String, Object> updates, String queryId) {
        long startTime = System.currentTimeMillis();
        PostgresRecord record = postgresRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(record);
        updates.forEach((key, value) -> {
            if (beanWrapper.isWritableProperty(key)) {
                beanWrapper.setPropertyValue(key, value);
            }
        });

        PostgresRecord updatedRecord = postgresRepository.save(record);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", 1, endTime - startTime, "postgres", queryId);
        return updatedRecord;
    }

    @Override
    @Transactional
    public void deleteRecord(Long id, String queryId) {
        long startTime = System.currentTimeMillis();
        if (!postgresRepository.existsById(id)) {
            throw new IllegalArgumentException("Record not found");
        }
        postgresRepository.deleteById(id);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Delete", 1, endTime - startTime, "postgres", queryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostgresRecord> getLastRecords(int count, String queryId) {
        long startTime = System.currentTimeMillis();
        List<PostgresRecord> records = postgresRepository.findAll(PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "date"))).getContent();
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "postgres", queryId);
        return records;
    }

    @Override
    @Transactional
    public List<PostgresRecord> updateLastRecords(Map<String, Object> updates, String queryId) {
        long startTime = System.currentTimeMillis();
        List<PostgresRecord> recordsToUpdate = postgresRepository.findAll(PageRequest.of(0, 10000, Sort.by(Sort.Direction.DESC, "date"))).getContent();
        List<PostgresRecord> updatedRecords = new ArrayList<>();

        for (PostgresRecord record : recordsToUpdate) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(record);
            updates.forEach((key, value) -> {
                if (beanWrapper.isWritableProperty(key)) {
                    beanWrapper.setPropertyValue(key, value);
                }
            });
            updatedRecords.add(postgresRepository.save(record));
        }

        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", recordsToUpdate.size(), endTime - startTime, "postgres", queryId);
        return updatedRecords;
    }

    @Override
    @Transactional
    public void deleteLastRecords(int count, String queryId) {
        long startTime = System.currentTimeMillis();
        List<PostgresRecord> recordsToDelete = postgresRepository.findAll(PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "date"))).getContent();
        postgresRepository.deleteAll(recordsToDelete);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Delete", recordsToDelete.size(), endTime - startTime, "postgres", queryId);
    }

    @Override
    @Transactional
    public List<PostgresRecord> insertRecords(List<PostgresRecord> records, String queryId) {
        long startTime = System.currentTimeMillis();
        List<PostgresRecord> insertedRecords = postgresRepository.saveAll(records);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Insert", insertedRecords.size(), endTime - startTime, "postgres", queryId);
        return insertedRecords;
    }
}