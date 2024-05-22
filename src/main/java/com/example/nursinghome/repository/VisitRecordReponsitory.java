package com.example.nursinghome.repository;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VisitRecordReponsitory extends JpaRepository<VisitRecord, Long> {
    @Query("SELECT v.id,v.visitor.name,v.visited.name,v.timeOfDay,v.visitDate FROM VisitRecord v WHERE v.visitor = :visitor AND v.visitDate = :visitDate")
    List<Object[]> findVisitRecordByVisitorAndVisitDate(User visitor, Date visitDate);

    @Query("SELECT v.id,v.visitor.name,v.visited.name,v.timeOfDay,v.visitDate FROM VisitRecord v WHERE v.visitor = :visitor ORDER BY v.visitDate DESC")
    List<Object[]> findVisitRecordByVisitor(User visitor);
}
