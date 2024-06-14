package com.example.nursinghome.controller;

import com.example.nursinghome.auth.RegisterRequest;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.repository.UserRepository;
import com.example.nursinghome.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:63344")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/get_all_user")
    public ResponseEntity<?> getAllUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getAllUser(request));
    }

    @GetMapping("/get_user")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUser(request));
    }

    @GetMapping("/get_user_by_id")
    public ResponseEntity<?> getUserById(HttpServletRequest request, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(request, id));
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
        return ResponseEntity.ok(userService.uploadImageForUser(httpServletRequest, id, file));
    }

    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(HttpServletRequest httpServletRequest, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(httpServletRequest, user));
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<?> deleteUser(HttpServletRequest httpServletRequest, @PathVariable("id") Long id) {
        userService.deleteUser(httpServletRequest, id);
        return ResponseEntity.ok("Delete user successfully");
    }

    @GetMapping("/get_family_member_by_id")
    public ResponseEntity<?> getFamilyMemberById(HttpServletRequest request, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userRepository.getFamilyUserByIdUser(id));
    }
}