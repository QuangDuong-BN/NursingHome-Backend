package com.example.nursinghome.controller;

import com.example.nursinghome.service.ServiceInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/service-info")
public class ServiceInfoController {
    private final ServiceInfoService serviceInfoService;
    @PostMapping("/add")
    public String AddServiceInfo(HttpServletRequest request, @RequestParam("name") String name,@RequestParam("descriptionService") String descriptionService, @RequestParam("file") MultipartFile file) {
        serviceInfoService.AddServiceInfo(request, name, descriptionService, file);
        return "success";
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> GetServiceInfo(HttpServletRequest request) {
        return ResponseEntity.ok(serviceInfoService.getAllServiceInfo());
    }
}
