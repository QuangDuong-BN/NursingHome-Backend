package com.example.nursinghome.controller;

import com.example.nursinghome.service.StatisticsService;
import com.example.nursinghome.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final UserService userService;
    private final StatisticsService statisticsService;

    @GetMapping("/count_user")
    public ResponseEntity<Integer> countUser(HttpServletRequest request){
        return ResponseEntity.ok(statisticsService.countUser(request));
    }

    @GetMapping("/count_staff")
    public ResponseEntity<Integer> countStaff(HttpServletRequest request){
        return ResponseEntity.ok(statisticsService.countStaff(request));
    }

    @GetMapping("/countRevenue")
    public ResponseEntity<Double> countRevenue(HttpServletRequest request){
        return ResponseEntity.ok(statisticsService.countRevenue(request));
    }


}
