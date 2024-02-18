package com.example.nursinghome.controller;

import com.example.nursinghome.service.ServiceInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
@RequiredArgsConstructor
@RestController
@RequestMapping("/serviceInfo")
public class ServiceInfoController {

    private final ServiceInfoService serviceInfoService;
    @PostMapping("/addService")
    public String AddService(HttpServletRequest request, String name, String descriptionService, MultipartFile file) {
        serviceInfoService.AddService(request, name, descriptionService, file);
        return "success";
    }
}
