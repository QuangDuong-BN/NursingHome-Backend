package com.example.nursinghome.controller;

import com.example.nursinghome.entity.VisitHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nursinghome.service.VisitHistoryService;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vist-history")
public class VisitController {
    private final VisitHistoryService visitHistoryService;

    @PostMapping("/save")
    public ResponseEntity<VisitHistory> saveVisitHistory(@RequestBody VisitHistory visitHistory) {
        VisitHistory savedVisitHistory = visitHistoryService.saveVisitHistory(visitHistory);
        return ResponseEntity.ok(savedVisitHistory);
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<List<VisitHistory>> getVisitHistoryByDate(@RequestParam Date visitDate) {
        List<VisitHistory> visitHistories = visitHistoryService.getVisitHistoryByDate(visitDate);
        return ResponseEntity.ok(visitHistories);
    }

}
