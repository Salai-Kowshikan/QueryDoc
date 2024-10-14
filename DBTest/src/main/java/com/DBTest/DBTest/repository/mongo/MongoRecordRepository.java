package com.DBTest.DBTest.repository.mongo;

import com.DBTest.DBTest.entity.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface MongoRecordRepository extends MongoRepository<Record, String> {

    @Query(value = "{}", fields = "{ '_id': 0 }")
    Page<Record> findAllWithoutId(Pageable pageable);
}