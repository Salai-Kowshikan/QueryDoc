package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.Record;
import com.DBTest.DBTest.repository.Postgres.PostgresRepository;
import com.DBTest.DBTest.repository.mongo.MongoRecordRepository;
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
        if ("mongo".equalsIgnoreCase(dbType)) {
            return mongoRecordRepository.findAllWithoutId(PageRequest.of(page, 1000)).getContent();
        } else {
            return postgresRepository.findAll(PageRequest.of(page, 1000)).getContent();
        }
    }

    @Override
    public List<Record> getRecordsByDateRange(LocalDate startDate, LocalDate endDate) {
        return postgresRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Record saveRecord(Record record) {
        return postgresRepository.save(record);
    }

    @Override
    @Transactional
    public Record updateRecord(Long id, Map<String, Object> updates) {
        Record record = postgresRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(record);
        updates.forEach((key, value) -> {
            if (beanWrapper.isWritableProperty(key)) {
                beanWrapper.setPropertyValue(key, value);
            }
        });

        return postgresRepository.save(record);
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {
        if (!postgresRepository.existsById(id)) {
            throw new IllegalArgumentException("Record not found");
        }
        postgresRepository.deleteById(id);
    }
}