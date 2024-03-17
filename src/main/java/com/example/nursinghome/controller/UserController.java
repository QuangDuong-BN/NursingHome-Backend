package com.example.nursinghome.controller;

import com.example.nursinghome.auth.RegisterRequest;
import com.example.nursinghome.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get_all_user")
    public ResponseEntity<?> getAllUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getAllUser(request));
    }

    @GetMapping("/get_user")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUserByID(request));
    }

    @PostMapping("/register_for_family_member")
    public ResponseEntity<?> registerForFamilyMember(HttpServletRequest httpServletRequest, @RequestBody RegisterRequest request) throws IOException {
        return ResponseEntity.ok(userService.registerForFamilyMember(httpServletRequest, request));
    }

    @GetMapping("/get_list_user_by_family_member")
    public ResponseEntity<?> getListUserByFamilyMember(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userService.getListUserByFamilyMember(httpServletRequest));
    }

    @PostMapping("/upload_image_for_user")
    public ResponseEntity<?> uploadImageForUser(HttpServletRequest httpServletRequest, @RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(userService.uploadImageForUser(httpServletRequest, id,file));
    }
}
