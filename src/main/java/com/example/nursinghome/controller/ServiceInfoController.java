package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.ServiceInfoRequest;
import com.example.nursinghome.entitydto.ServiceInfoResponse;
import com.example.nursinghome.service.ServiceInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:63344")
@RequestMapping("/service_info")
public class ServiceInfoController {
    private final ServiceInfoService serviceInfoService;
    @PostMapping("/add")
    public ResponseEntity<?> addServiceInfo(HttpServletRequest request, @ModelAttribute ServiceInfoRequest serviceInfoRequest) {
        serviceInfoService.AddServiceInfo(request, serviceInfoRequest);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getServiceInfo1(HttpServletRequest request) {
        return ResponseEntity.ok(serviceInfoService.getAllServiceInfo());
    }

    @GetMapping("/get_all_for_list_service_info")
    public ResponseEntity<?> getServiceInfo2(HttpServletRequest request) {
        return ResponseEntity.ok(serviceInfoService.getAllServiceInfoProjection());
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<?> getServiceInfo3(HttpServletRequest request, @RequestParam Long id) {
        return ResponseEntity.ok(serviceInfoService.getServiceInfoByid(id));
    }

}
