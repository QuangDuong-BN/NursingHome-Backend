package com.example.nursinghome.repository;

import com.example.nursinghome.model.HealthRecord;
import com.example.nursinghome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long>{
    @Query("SELECT h FROM HealthRecord h WHERE h.user = ?1 ORDER BY h.date DESC LIMIT 1")
    List<HealthRecord> findAllByUser(User user);
}
