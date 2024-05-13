package com.example.nursinghome.service;

import com.example.nursinghome.entity.VisitHistory;
import com.example.nursinghome.repository.VisitReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitHistoryService {
    private final VisitReponsitory visitReponsitory;

    public List<VisitHistory> getVisitHistoryByDate(Date visitDate) {
        return visitReponsitory.findByVisitDate(visitDate);
    }

    public VisitHistory saveVisitHistory(VisitHistory visitHistory) {
        return visitReponsitory.save(visitHistory);
    }
}
