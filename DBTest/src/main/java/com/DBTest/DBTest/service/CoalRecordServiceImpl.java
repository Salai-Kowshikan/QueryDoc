package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.Record;
import com.DBTest.DBTest.repository.CoalRepository;
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

    private final CoalRepository coalRepository;

    @Autowired
    public CoalRecordServiceImpl(CoalRepository coalRepository) {
        this.coalRepository = coalRepository;
    }

    @Override
    public List<Record> getAllRecords(int page) {
        return coalRepository.findAll(PageRequest.of(page, 1000)).getContent();
    }

    @Override
    public List<Record> getRecordsByDateRange(LocalDate startDate, LocalDate endDate) {
        return coalRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Record saveRecord(Record record) {
        return coalRepository.save(record);
    }

    @Override
    @Transactional
    public Record updateRecord(Long id, Map<String, Object> updates) {
        Record record = coalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(record);
        updates.forEach((key, value) -> {
            if (beanWrapper.isWritableProperty(key)) {
                beanWrapper.setPropertyValue(key, value);
            }
        });

        return coalRepository.save(record);
    }

    @Override
    @Transactional
    public void deleteRecord(Long id) {
        if (!coalRepository.existsById(id)) {
            throw new IllegalArgumentException("Record not found");
        }
        coalRepository.deleteById(id);
    }
}