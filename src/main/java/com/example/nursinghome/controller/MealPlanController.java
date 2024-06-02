package com.example.nursinghome.controller;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.entitydto.MealPlanDTO;
import com.example.nursinghome.service.MealPlanService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@CrossOrigin(origins = "http://localhost:63344")
@RequiredArgsConstructor
@RestController
@RequestMapping("meal_plan")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    @GetMapping("/get_all_by_user")
    public ResponseEntity<?> getAllByUser(HttpServletRequest request, @RequestParam("id") Long userId) {
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(mealPlanService.getAllMealPlan(request, user));
    }

    @GetMapping("/get_by_date")
    public ResponseEntity<?> getAllByUserAndDate(HttpServletRequest request, @RequestParam("date") Date date) {
        return ResponseEntity.ok(mealPlanService.getMealPlanByDate(request, date));
    }

    @PostMapping("/add_by_user")
    public ResponseEntity<?> addByUser(HttpServletRequest request, @RequestBody MealPlanDTO mealPlanDTO) {
        return ResponseEntity.ok(mealPlanService.addMealPlan(request, mealPlanDTO)) ;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMealPlan(HttpServletRequest request, @RequestParam("id") Long id) {
        return ResponseEntity.ok(mealPlanService.deleteMealPlan(request, id));
    }
}
