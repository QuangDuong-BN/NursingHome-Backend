package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.CostsIncurredDTO;
import com.example.nursinghome.entitydto.HealthRecordDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nursinghome.service.CostsIncurredService;
@RequiredArgsConstructor
@RestController
@RequestMapping("costs_incurred")
public class CostsIncurredController {
    private final CostsIncurredService costsIncurredService;

    @PostMapping("/add")
    public ResponseEntity<?> AddostsIncurred(HttpServletRequest request, @RequestBody CostsIncurredDTO costsIncurredDTO) {
        costsIncurredService.addCostsIncurred(request, costsIncurredDTO);
        return ResponseEntity.ok("success");
    }
}
