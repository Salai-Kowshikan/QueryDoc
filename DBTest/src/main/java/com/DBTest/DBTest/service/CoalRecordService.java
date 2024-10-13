package com.DBTest.DBTest.service;

import com.DBTest.DBTest.entity.Record;
import java.util.List;

public interface CoalRecordService {
    List<Record> getAllRecords(int page);
}
