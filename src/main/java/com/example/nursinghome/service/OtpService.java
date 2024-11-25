package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.viewmodel.KafkaMailVM;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Quang Duong
 * @summary java 21
 */
@AllArgsConstructor
@Service
public class OtpService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final KafkaTemplate<String, KafkaMailVM> kafkaTemplate;

    private static final long OTP_EXPIRATION_MINUTES = 10;

    // Tạo mã OTP ngẫu nhiên
    public String generateOtp() {
        Integer randomValue = new Random().nextInt(999999);
        return String.format("%06d", randomValue);
    }

    // Lưu OTP va gửi OTP qua email
    public void saveOtpAndSendOtpToEmail(String email) {
        String otp = generateOtp();
        KafkaMailVM kafkaMailVM = new KafkaMailVM(email, "OTP", "OTP for " + email + " is: " + otp);
        redisTemplate.opsForValue().set("user:otp:" + email + ":", otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        kafkaTemplate.send("send-email", kafkaMailVM);
    }

    public Boolean verifyOtp(String email, String otp) {
        String key = "user:otp:" + email + ":";
        String savedOtp = (String) redisTemplate.opsForValue().get(key);
        return otp.equals(savedOtp);
    }

    // Xóa OTP (nếu cần)
    public void deleteOtp(String key) {
        redisTemplate.delete(key);
    }
}
