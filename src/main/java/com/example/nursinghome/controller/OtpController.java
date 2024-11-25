package com.example.nursinghome.controller;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;
    private final JwtService jwtService;

    // API tạo OTP
    @GetMapping("/generate")
    public ResponseEntity<?> generateOtp(HttpServletRequest httpServletRequest) {
        String token = jwtService.getTokenFromHttpServletRequest(httpServletRequest);
        String email = jwtService.extractUsername(token);
        otpService.saveOtpAndSendOtpToEmail(email);
        return ResponseEntity.ok("OTP is sent to your email!");
    }

    // API xác thực OTP
    @PostMapping("/verify")
    public String verifyOtp(HttpServletRequest httpServletRequest, @RequestParam("otp") String otp) {
        String token = jwtService.getTokenFromHttpServletRequest(httpServletRequest);
        String email = jwtService.extractUsername(token);
        if (otpService.verifyOtp(email, otp)) {
            return "OTP is valid!";
        } else {
            return "Invalid OTP!";
        }
    }
}
