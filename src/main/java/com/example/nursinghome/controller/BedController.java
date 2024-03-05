package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.BedDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nursinghome.service.BedService;
@RequiredArgsConstructor
@RestController
@RequestMapping("/bed")
public class BedController {
    private final BedService bedService;

    @PostMapping("/add")
    public ResponseEntity<?> addBed(HttpServletRequest httpServletRequest, @RequestBody BedDTO bedDTO) {
        bedService.addBed(httpServletRequest,bedDTO);
        return ResponseEntity.ok("success");
    }
}
