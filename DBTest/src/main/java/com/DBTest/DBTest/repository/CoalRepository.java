package com.DBTest.DBTest.repository;

import com.DBTest.DBTest.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoalRepository extends JpaRepository<Record, Long> {
    Page<Record> findAll(Pageable pageable);
}