package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.MongoRecord;
import com.DBTest.DBTest.repository.mongo.MongoRecordRepository;
import com.DBTest.DBTest.util.CsvLogger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<MongoRecord> saveRecords(List<MongoRecord> records, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> savedRecords = mongoRecordRepository.saveAll(records);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Create", records.size(), endTime - startTime, "mongo", queryId);
        return savedRecords;
    }

    @Override
    @Transactional
    public void deleteLastRecords(int count, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> recordsToDelete = mongoRecordRepository.findAll(PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "date"))).getContent();
        mongoRecordRepository.deleteAll(recordsToDelete);
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Delete", recordsToDelete.size(), endTime - startTime, "mongo", queryId);
    }

    @Override
    @Transactional
    public List<MongoRecord> updateRecords(List<Map<String, Object>> updates, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> updatedRecords = new ArrayList<>();
        for (Map<String, Object> update : updates) {
            String id = (String) update.get("id");
            MongoRecord record = mongoRecordRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Record not found"));

            BeanWrapper beanWrapper = new BeanWrapperImpl(record);
            update.forEach((key, value) -> {
                if (beanWrapper.isWritableProperty(key)) {
                    beanWrapper.setPropertyValue(key, value);
                }
            });

            updatedRecords.add(mongoRecordRepository.save(record));
        }
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", updates.size(), endTime - startTime, "mongo", queryId);
        return updatedRecords;
    }
    @Override
    @Transactional
    public List<MongoRecord> updateLastRecords(Map<String, Object> updates, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> recordsToUpdate = mongoRecordRepository.findAll(PageRequest.of(0, 10000, Sort.by(Sort.Direction.DESC, "date"))).getContent();
        List<MongoRecord> updatedRecords = new ArrayList<>();

        for (MongoRecord record : recordsToUpdate) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(record);
            updates.forEach((key, value) -> {
                if (beanWrapper.isWritableProperty(key)) {
                    beanWrapper.setPropertyValue(key, value);
                }
            });
            updatedRecords.add(mongoRecordRepository.save(record));
        }

        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", recordsToUpdate.size(), endTime - startTime, "mongo", queryId);
        return updatedRecords;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MongoRecord> getLastRecords(int count, String queryId) {
        long startTime = System.currentTimeMillis();
        List<MongoRecord> records = mongoRecordRepository.findAll(PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "date"))).getContent();
        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Read", records.size(), endTime - startTime, "mongo", queryId);
        return records;
    }

    @Override
    @Transactional
    public List<MongoRecord> searchAndUpdateRecords(Map<String, Object> findCriteria, Map<String, Object> updateFields, String queryId) {
        long startTime = System.currentTimeMillis();

        // Create criteria
        List<Criteria> criteriaList = findCriteria.entrySet().stream()
                .map(entry -> Criteria.where(entry.getKey()).is(entry.getValue()))
                .collect(Collectors.toList());
        Criteria criteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));

        // Find records to update
        List<MongoRecord> recordsToUpdate = mongoRecordRepository.findByCriteria(criteria);

        // Perform update
        recordsToUpdate.forEach(record -> {
            BeanWrapper beanWrapper = new BeanWrapperImpl(record);
            updateFields.forEach((key, value) -> {
                if (beanWrapper.isWritableProperty(key)) {
                    beanWrapper.setPropertyValue(key, value);
                }
            });
            mongoRecordRepository.save(record);
        });

        long endTime = System.currentTimeMillis();
        CsvLogger.logQuery("Update", recordsToUpdate.size(), endTime - startTime, "mongo", queryId);
        return recordsToUpdate;
    }
}
