package com.example.nursinghome.service;
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
    final private KafkaTemplate<String, String> kafkaTemplate;

    private static final long OTP_EXPIRATION_MINUTES = 10;

    // Tạo mã OTP ngẫu nhiên
    public String generateOtp() {
        return String.valueOf(new Random().nextInt(999999));
    }

    // Gửi OTP đến email
    public void sendOtpToEmail(String email, String otp) {
        kafkaTemplate.send("send-email", "OTP for " + email + " is: " + otp);
    }

    // Lưu OTP
    public void saveOtpAndSendOtpToEmail(String key) {
        String otp=generateOtp();
        redisTemplate.opsForValue().set("user:otp:", otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
//        kafkaTemplate.send("send-email", "OTP for " + email + " is: " + otp);

    }

    // Lấy OTP
    public String getOtp(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    // Xóa OTP (nếu cần)
    public void deleteOtp(String key) {
        redisTemplate.delete(key);
    }
}
