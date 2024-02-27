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
@RequestMapping("/service_info")
public class ServiceInfoController {
    private final ServiceInfoService serviceInfoService;
    @PostMapping("/add")
    public ResponseEntity<?> AddServiceInfo(HttpServletRequest request, @ModelAttribute ServiceInfoRequest serviceInfoRequest) {
        serviceInfoService.AddServiceInfo(request, serviceInfoRequest);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> GetServiceInfo(HttpServletRequest request) {
        return ResponseEntity.ok(serviceInfoService.getAllServiceInfo());
    }
}
