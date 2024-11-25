package com.example.nursinghome.controller;

import com.example.nursinghome.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    // API tạo OTP
    @PostMapping("/generate")
    public String generateOtp(@RequestParam String phoneNumber) {
        // Tạo mã OTP ngẫu nhiên
        String otp = String.valueOf(new Random().nextInt(999999));
        otpService.saveOtp(phoneNumber, otp);
        return "OTP for " + phoneNumber + " is: " + otp;
    }

    // API xác thực OTP
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        String cachedOtp = otpService.getOtp(phoneNumber);
        if (cachedOtp != null && cachedOtp.equals(otp)) {
            otpService.deleteOtp(phoneNumber); // Xóa OTP sau khi xác thực thành công
            return "OTP is valid!";
        }
        return "Invalid OTP!";
    }
}
