/* (C)2024 */
package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.ServiceRecordDTO;
import com.example.nursinghome.repository.ServiceRecordRepository;
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
    private final ServiceRecordRepository serviceRecordRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addServiceRecord(
            HttpServletRequest httpServletRequest, @RequestBody ServiceRecordDTO serviceRecordDTO) {
        serviceRecordService.addServiceRecord(httpServletRequest, serviceRecordDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllServiceRecord(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(serviceRecordRepository.findAll());
    }

    @GetMapping("/get_all_by_id")
    public ResponseEntity<?> getAllServiceRecordByResidentId(
            HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(serviceRecordService.getListServiceRecord(httpServletRequest));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<?> getServiceRecordById(
            HttpServletRequest httpServletRequest, @RequestParam Long id) {
        return ResponseEntity.ok(serviceRecordService.getServiceRecordById(httpServletRequest, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteServiceRecord(
            HttpServletRequest httpServletRequest, @RequestParam Long id) {
        return ResponseEntity.ok(serviceRecordService.deleteEntityById(httpServletRequest, id));
    }

    @GetMapping("/update_record_status")
    public ResponseEntity<?> updateRecordStatus(
            HttpServletRequest httpServletRequest, @RequestParam Long id) {
        return ResponseEntity.ok(
                serviceRecordService.updateServiceRecordById(httpServletRequest, id));
    }
}
