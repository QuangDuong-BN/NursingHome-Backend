package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.MealPlan;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.entitydto.MealPlanDTO;
import com.example.nursinghome.repository.HealthRecordRepository;
import com.example.nursinghome.repository.MealPlanRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MealPlanService {

    private final UserRepository UserRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final MealPlanRepository mealPlanRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public void addMealPlan(HttpServletRequest httpServletRequest, MealPlanDTO mealPlanDTO) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        User creator = userRepository.getUerByUserName(username);

        var mealPlan = MealPlan.builder()
                .breakfast(mealPlanDTO.getBreakfast())
                .lunch(mealPlanDTO.getLunch())
                .dinner(mealPlanDTO.getDinner())
                .date(mealPlanDTO.getDate())
                .note(mealPlanDTO.getNote())
                .user(UserRepository.findById(mealPlanDTO.getUserId()).orElse(null))
                .creator(creator)
                .build();
        mealPlanRepository.save(mealPlan);
    }

//    public List<MealPlanDTO> getAllHealthRecord() {
//        List<MealPlanDTO> healthRecords = healthRecordRepository.findAll();
//        return healthRecords.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
    public List<MealPlanDTO> getAllMealPlanByUser(HttpServletRequest httpServletRequest, User user) {
        List<MealPlan> mealPlans = mealPlanRepository.findAllByUser(user);
        return mealPlans.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<MealPlanDTO> getMealPlanByUserAndDate(HttpServletRequest httpServletRequest, User user, Timestamp date) {
        List<MealPlan> mealPlans = mealPlanRepository.findAllByUserAndDate(user, date);
        return mealPlans.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MealPlanDTO mapToDTO(MealPlan mealPlan) {
        MealPlanDTO mealPlanDTO = new MealPlanDTO();
        mealPlanDTO.setUserId(mealPlan.getUser().getId());
        mealPlanDTO.setBreakfast(mealPlan.getBreakfast());
        mealPlanDTO.setLunch(mealPlan.getLunch());
        mealPlanDTO.setDinner(mealPlan.getDinner());
        mealPlanDTO.setDate(mealPlan.getDate());
        mealPlanDTO.setNote(mealPlan.getNote());
        return mealPlanDTO;
    }

//    private HealthRecordDTO mapToDTO(HealthRecord healthRecord) {
//        HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
//        healthRecordDTO.setWeight(healthRecord.getWeight());
//        healthRecordDTO.setBloodPressure(healthRecord.getBloodPressure());
//        healthRecordDTO.setHeartbeat(healthRecord.getHeartbeat());
//        healthRecordDTO.setTemperature(healthRecord.getTemperature());
//        healthRecordDTO.setAwareness(healthRecord.getAwareness());
//        healthRecordDTO.setMood(healthRecord.getMood());
//        healthRecordDTO.setDate(healthRecord.getDate());
//        healthRecordDTO.setNote(healthRecord.getNote());
//        return healthRecordDTO;
//    }
}
