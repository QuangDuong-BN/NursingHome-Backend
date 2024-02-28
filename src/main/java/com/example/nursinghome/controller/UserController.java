package com.example.nursinghome.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nursinghome.service.UserService;
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get_all_user")
    public ResponseEntity<?> getAllUser(HttpServletRequest  request){
        return ResponseEntity.ok(userService.getAllUser(request));
    }
    @GetMapping("/get_user")
    public ResponseEntity<?> getUser(HttpServletRequest  request){
        return ResponseEntity.ok(userService.getUserByID(request));
    }
}
