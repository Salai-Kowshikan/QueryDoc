package com.DBTest.DBTest.repository.Postgres;

import com.DBTest.DBTest.entity.PostgresRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostgresRepository extends JpaRepository<PostgresRecord, Long> {
    Page<PostgresRecord> findAll(Pageable pageable);
    Page<PostgresRecord> findByStateName(String stateName, Pageable pageable);

}