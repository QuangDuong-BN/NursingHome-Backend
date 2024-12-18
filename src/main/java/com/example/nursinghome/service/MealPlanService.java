package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.MealPlan;
import com.example.nursinghome.model.User;
import com.example.nursinghome.entitydto.MealPlanDTO;
import com.example.nursinghome.exception.ConflictException;
import com.example.nursinghome.repository.HealthRecordRepository;
import com.example.nursinghome.repository.MealPlanRepository;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MealPlanService {

    private final UserRepository UserRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final MealPlanRepository mealPlanRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String addMealPlan(HttpServletRequest httpServletRequest, MealPlanDTO mealPlanDTO) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        User creator = userRepository.getUerByUserName(username);
        Long cout = mealPlanRepository.countByDate(mealPlanDTO.getDate());
        if (cout > 0) {
            throw new ConflictException("Bạn đã tạo kế hoạch ăn cho ngày này rồi!");
        }
        var mealPlan = MealPlan.builder()
                .breakfast(mealPlanDTO.getBreakfast())
                .lunch(mealPlanDTO.getLunch())
                .dinner(mealPlanDTO.getDinner())
                .date(mealPlanDTO.getDate())
                .note(mealPlanDTO.getNote())
                .creator(creator)
                .build();
        mealPlanRepository.save(mealPlan);
        return "success";
    }

    //    public List<MealPlanDTO> getAllHealthRecord() {
//        List<MealPlanDTO> healthRecords = healthRecordRepository.findAll();
//        return healthRecords.stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
    public List<MealPlanDTO> getAllMealPlan(HttpServletRequest httpServletRequest, User user) {
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

    public Object[] getMealPlanByDate(HttpServletRequest httpServletRequest, Date date) {
        Object[] mealPlans = mealPlanRepository.findAllByDate(date);
        return mealPlans;
    }

    public String deleteMealPlan(HttpServletRequest httpServletRequest, Long id) {
        // Kiểm tra xem đối tượng cần xóa có tồn tại hay không
        Optional<MealPlan> entity = mealPlanRepository.findById(id);
        if (entity.isPresent()) {
            // Thực hiện xóa đối tượng
            mealPlanRepository.deleteById(id);

            // Kiểm tra xem đối tượng còn tồn tại sau khi xóa hay không
            entity = mealPlanRepository.findById(id);
            if (!entity.isPresent()) {
                // Nếu đối tượng không còn tồn tại, trả về true
                return "success";
            }
        }
        // Nếu đối tượng không tồn tại hoặc xóa không thành công, trả về false
        throw new ConflictException("Xóa thất bại, yêu cầu thực hiện lại");
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

    public MealPlan updateMealPlan(MealPlanDTO mealPlanDTO) {
        MealPlan mealPlan = mealPlanRepository.findById(mealPlanDTO.getId())
                .orElseThrow(() -> new RuntimeException("MealPlan not found with id: " + mealPlanDTO.getId()));

        mealPlan.setBreakfast(mealPlanDTO.getBreakfast());
        mealPlan.setLunch(mealPlanDTO.getLunch());
        mealPlan.setDinner(mealPlanDTO.getDinner());
        mealPlan.setDate(mealPlanDTO.getDate());
        mealPlan.setNote(mealPlanDTO.getNote());
        return mealPlanRepository.save(mealPlan);
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
