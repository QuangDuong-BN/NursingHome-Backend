package com.example.nursinghome.controller;

import com.example.nursinghome.entity.VisitRecord;
import com.example.nursinghome.entitydto.VisitRecordDTO;
import com.example.nursinghome.service.VisitRecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("/visitRecord")
public class VisitRecordController {
    private final VisitRecordService visitHistoryService;

    @PostMapping("/add")
    public ResponseEntity<?> saveVisitHistory(HttpServletRequest httpServletRequest, @RequestBody VisitRecordDTO visitRecordDTO) {
        VisitRecord savedVisitHistory = visitHistoryService.addVisitHistory(httpServletRequest, visitRecordDTO);
        return ResponseEntity.ok(savedVisitHistory);
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<?> getVisitHistoryByDate(HttpServletRequest httpServletRequest, @RequestParam("visitDate") Date visitDate) {
        return ResponseEntity.ok(visitHistoryService.getVisitHistoryByDate(httpServletRequest, visitDate));
    }

    @GetMapping("/get-all-for-user")
    public ResponseEntity<?> getAllforUser(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(visitHistoryService.getVisitforUser(httpServletRequest));
    }

}
