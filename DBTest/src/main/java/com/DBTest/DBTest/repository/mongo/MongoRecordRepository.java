package com.DBTest.DBTest.repository.mongo;

import com.DBTest.DBTest.entity.MongoRecord;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Repository
public interface MongoRecordRepository extends MongoRepository<MongoRecord, String> {

    @Query(value = "{}", fields = "{ '_id': 0 }")
    Page<MongoRecord> findAllWithoutId(Pageable pageable);
    Page<MongoRecord> findByStateName(String stateName, Pageable pageable);
    @Query("{}")
    List<MongoRecord> findByCriteria(Criteria criteria);
}