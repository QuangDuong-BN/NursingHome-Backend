package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.HealthRecord;
import com.example.nursinghome.model.User;
import com.example.nursinghome.entitydto.HealthRecordDTO;
import com.example.nursinghome.repository.HealthRecordRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HealthRecordService {

    private final UserRepository UserRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    public void addHealthRecord(HttpServletRequest httpServletRequest, HealthRecordDTO healthRecordDTO) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        User creator = userRepository.getUerByUserName(username);

        var healthRecord = HealthRecord.builder()
                .weight(healthRecordDTO.getWeight())
                .bloodPressure(healthRecordDTO.getBloodPressure())
                .heartbeat(healthRecordDTO.getHeartbeat())
                .temperature(healthRecordDTO.getTemperature())
                .awareness(healthRecordDTO.getAwareness())
                .mood(healthRecordDTO.getMood())
                .date(healthRecordDTO.getDate())
                .note(healthRecordDTO.getNote())
                .user(UserRepository.findById(healthRecordDTO.getUserId()).orElse(null))
                .creator(creator)
                .build();
        healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecordDTO> getAllHealthRecord() {
        List<HealthRecord> healthRecords = healthRecordRepository.findAll();
        return healthRecords.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<HealthRecordDTO> getAllHealthRecordById(HttpServletRequest httpServletRequest, User user) {
        List<HealthRecord> healthRecords = healthRecordRepository.findAllByUser(user);
        return healthRecords.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

//    public List<HealthRecordDTO> getAllHealthRecordByToken(HttpServletRequest httpServletRequest) {
//
//        List<HealthRecord> healthRecords = healthRecordRepository.findAllByUser(user);
//        return healthRecords.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }


    private HealthRecordDTO mapToDTO(HealthRecord healthRecord) {
        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
        healthRecordDTO.setUserId(healthRecord.getUser().getId());
        healthRecordDTO.setAddress(healthRecord.getUser().getAddress());
        healthRecordDTO.setName(healthRecord.getUser().getName());
        healthRecordDTO.setImage_url(healthRecord.getUser().getImageUrl());
        healthRecordDTO.setWeight(healthRecord.getWeight());
        healthRecordDTO.setBloodPressure(healthRecord.getBloodPressure());
        healthRecordDTO.setHeartbeat(healthRecord.getHeartbeat());
        healthRecordDTO.setTemperature(healthRecord.getTemperature());
        healthRecordDTO.setAwareness(healthRecord.getAwareness());
        healthRecordDTO.setMood(healthRecord.getMood());
        healthRecordDTO.setDate(healthRecord.getDate());
        healthRecordDTO.setNote(healthRecord.getNote());
        return healthRecordDTO;
    }
}
