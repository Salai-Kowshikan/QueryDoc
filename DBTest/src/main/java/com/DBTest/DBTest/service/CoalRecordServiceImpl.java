package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.Record;
import com.DBTest.DBTest.repository.Postgres.PostgresRepository;
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
public class CoalRecordServiceImpl implements CoalRecordService {

    private final PostgresRepository postgresRepository;
    private final MongoRecordRepository mongoRecordRepository;

    @Autowired
    public CoalRecordServiceImpl(PostgresRepository postgresRepository, MongoRecordRepository mongoRecordRepository) {
        this.postgresRepository = postgresRepository;
        this.mongoRecordRepository = mongoRecordRepository;
    }

    @Override
    public List<Record> getAllRecords(String dbType, int page) {
        long startTime = System.currentTimeMillis();
        List<Record> records;
        if ("mongo".equalsIgnoreCase(dbType)) {
            records = mongoRecordRepository.findAllWithoutId(PageRequest.of(page, 1000)).getContent();
        } else {
            records = postgresRepository.findAll(PageRequest.of(page, 1000)).getContent();
        }
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, dbType);
        return records;
    }

    @Override
    public List<Record> getRecordsByDateRange(LocalDate startDate, LocalDate endDate) {
        long startTime = System.currentTimeMillis();
        List<Record> records = postgresRepository.findByDateBetween(startDate, endDate);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "postgres");
        return records;
    }

    @Override
    public Record saveRecord(Record record) {
        long startTime = System.currentTimeMillis();
        Record savedRecord = postgresRepository.save(record);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Create", 1, endTime - startTime, "postgres");
        return savedRecord;
    }

    @Override
    @Transactional
    public Record updateRecord(Long id, Map<String, Object> updates) {
        long startTime = System.currentTimeMillis();
        Record record = postgresRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(record);
        updates.forEach((key, value) -> {
            if (beanWrapper.isWritableProperty(key)) {
                beanWrapper.setPropertyValue(key, value);
            }
        });

        Record updatedRecord = postgresRepository.save(record);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", 1, endTime - startTime, "postgres");
        return updatedRecord;
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {
        long startTime = System.currentTimeMillis();
        if (!postgresRepository.existsById(id)) {
            throw new IllegalArgumentException("Record not found");
        }
        postgresRepository.deleteById(id);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Delete", 1, endTime - startTime, "postgres");
    }
}