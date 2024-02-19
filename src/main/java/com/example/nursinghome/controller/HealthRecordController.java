package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.HealthRecordDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nursinghome.service.HealthRecordService;
@RequiredArgsConstructor
@RestController
@RequestMapping("health_record_info")
public class HealthRecordController {
    private final HealthRecordService healthRecordService;

    @GetMapping("/get_all")
    public ResponseEntity<?> GetHealthRecord(HttpServletRequest request) {
        return ResponseEntity.ok(healthRecordService.getAllHealthRecord());
    }

    @PostMapping("/add")
    public ResponseEntity<?> AddHealthRecord(HttpServletRequest request, @RequestBody HealthRecordDTO healthRecordRequest) {
        healthRecordService.addHealthRecord(request, healthRecordRequest);
        return ResponseEntity.ok("success");
    }
}
