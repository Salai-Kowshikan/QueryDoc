package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.Record;
import com.DBTest.DBTest.repository.CoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

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
}