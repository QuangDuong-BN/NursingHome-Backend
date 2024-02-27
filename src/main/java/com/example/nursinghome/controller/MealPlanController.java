package com.example.nursinghome.controller;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.entitydto.MealPlanDTO;
import com.example.nursinghome.entitydto.MealPlanRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nursinghome.service.MealPlanService;

import java.sql.Timestamp;

@RequiredArgsConstructor
@RestController
@RequestMapping("meal_plan")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    @GetMapping("/get_all_by_user")
    public ResponseEntity<?> getAllByUser(HttpServletRequest request, @RequestParam("id") Long userId) {
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(mealPlanService.getAllMealPlanByUser(request,user));
    }

    @GetMapping("/get_all_by_user_and_date")
    public ResponseEntity<?> getAllByUserAndDate(HttpServletRequest request, @RequestBody MealPlanRequest mealPlanRequest) {
        User user = new User();
        user.setId(mealPlanRequest.getId());
        return ResponseEntity.ok(mealPlanService.getMealPlanByUserAndDate(request,user,mealPlanRequest.getDate()));
    }


    @PostMapping("/add_by_user")
    public ResponseEntity<?> addByUser(HttpServletRequest request, @RequestBody MealPlanDTO mealPlanDTO ) {
        mealPlanService.addMealPlan(request, mealPlanDTO);
        return ResponseEntity.ok("success");
    }





}
