package com.example.nursinghome.repository;

import com.example.nursinghome.entity.HealthRecord;
import com.example.nursinghome.entity.MealPlan;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    @Query("SELECT h FROM MealPlan h WHERE h.user = ?1")
    List<MealPlan> findAllByUser(User user);
}
