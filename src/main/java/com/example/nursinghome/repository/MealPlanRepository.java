package com.example.nursinghome.repository;

import com.example.nursinghome.entity.HealthRecord;
import com.example.nursinghome.entity.MealPlan;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    @Query("SELECT h FROM MealPlan h WHERE h.user = ?1")
    List<MealPlan> findAllByUser(User user);

    // TODO: Add method to find all meal plans by date
    @Query("SELECT m FROM MealPlan m WHERE DATE(m.date) = DATE(:date)")
    List<MealPlan> findAllByDate(@Param("date") Timestamp date);

    @Query("SELECT m FROM MealPlan m WHERE m.user = ?1 AND DATE(m.date) = DATE(?2)")
    List<MealPlan> findAllByUserAndDate(User user, Timestamp date);
}
