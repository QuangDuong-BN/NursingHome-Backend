package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.ServiceRecordDTO;
import com.example.nursinghome.service.ServiceRecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/service_record")
public class ServiceRecordController {
    private final ServiceRecordService serviceRecordService;
    @PostMapping("/add")
    public ResponseEntity<?> addServiceRecord(HttpServletRequest httpServletRequest,@RequestBody ServiceRecordDTO serviceRecordDTO) {
        serviceRecordService.addServiceRecord(httpServletRequest, serviceRecordDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get_all_by_id")
    public ResponseEntity<?> getAllServiceRecordByResidentId(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(serviceRecordService.getListServiceRecord(httpServletRequest));
    }
}
