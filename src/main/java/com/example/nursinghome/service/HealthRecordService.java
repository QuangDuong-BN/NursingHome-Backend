package com.example.nursinghome.service;

import com.example.nursinghome.entity.HealthRecord;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.entitydto.HealthRecordDTO;
import com.example.nursinghome.entitydto.ServiceInfoResponse;
import com.example.nursinghome.repository.HealthRecordRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HealthRecordService {

    private final UserRepository UserRepository;
    private final HealthRecordRepository healthRecordRepository;



    public void addHealthRecord(HttpServletRequest httpServletRequest,HealthRecordDTO healthRecordDTO) {
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
                .build();
        healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecordDTO> getAllHealthRecord() {
        List<HealthRecord> healthRecords = healthRecordRepository.findAll();
        return healthRecords.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<HealthRecordDTO> getAllHealthRecordByUser(HttpServletRequest httpServletRequest,User user) {
        List<HealthRecord> healthRecords = healthRecordRepository.findAllByUser(user);
        return healthRecords.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private HealthRecordDTO mapToDTO(HealthRecord healthRecord) {
        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
        healthRecordDTO.setUserId(healthRecord.getUser().getId());
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
