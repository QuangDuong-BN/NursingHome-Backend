package com.example.nursinghome.controller;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.entitydto.MealPlanDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nursinghome.service.MealPlanService;
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

    @PostMapping("/add_by_user")
    public ResponseEntity<?> addByUser(HttpServletRequest request, @RequestBody MealPlanDTO mealPlanDTO ) {
        mealPlanService.addMealPlan(request, mealPlanDTO);
        return ResponseEntity.ok("success");
    }



}
