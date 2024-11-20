/* (C)2024 */
package com.example.nursinghome.controller;

import com.example.nursinghome.auth.RegisterRequest;
import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.User;
import com.example.nursinghome.repository.UserRepository;
import com.example.nursinghome.service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get_all_user")
    public ResponseEntity<?> getAllUser() {
        log.info("usernname: " + SecurityContextHolder.getContext().getAuthentication().getName());
        SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/get_user")
    public ResponseEntity<?> getUser(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        Integer version= jwtService.extractVersionToken(token);
        log.error("Token: " + version);
        return ResponseEntity.ok(userService.getUser(httpServletRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get_user_by_id")
    public ResponseEntity<?> getUserById(HttpServletRequest request, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(request, id));
    }

    @PostMapping("/register_for_family_member")
    public ResponseEntity<?> registerForFamilyMember(
            HttpServletRequest httpServletRequest, @RequestBody RegisterRequest request)
            throws IOException, JOSEException {
        return ResponseEntity.ok(userService.registerForFamilyMember(httpServletRequest, request));
    }

    @GetMapping("/get_list_user_by_family_member")
    public ResponseEntity<?> getListUserByFamilyMember(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userService.getListUserByFamilyMember(httpServletRequest));
    }

    @PostMapping("/upload_image_for_user")
    public ResponseEntity<?> uploadImageForUser(
            HttpServletRequest httpServletRequest,
            @RequestParam("id") Long id,
            @RequestParam("file") MultipartFile file)
            throws IOException {
        return ResponseEntity.ok(userService.uploadImageForUser(httpServletRequest, id, file));
    }

    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(
            HttpServletRequest httpServletRequest, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(httpServletRequest, user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<?> deleteUser(
            HttpServletRequest httpServletRequest, @PathVariable("id") Long id) {
        userService.deleteUser(httpServletRequest, id);
        return ResponseEntity.ok("Delete user successfully");
    }

    @GetMapping("/get_family_member_by_id")
    public ResponseEntity<?> getFamilyMemberById(
            HttpServletRequest request, @RequestParam("id") Long id) {
        return ResponseEntity.ok(userRepository.getFamilyUserByIdUser(id));
    }
}
