package com.example.nursinghome.repository;

import com.example.nursinghome.entity.VisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VisitReponsitory extends JpaRepository<VisitHistory, Long> {

    List<VisitHistory> findByVisitDate(Date visitDate);
}
