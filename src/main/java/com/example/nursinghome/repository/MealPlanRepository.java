package com.example.nursinghome.repository;

import com.example.nursinghome.entity.HealthRecord;
import com.example.nursinghome.entity.MealPlan;
import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    @Query("SELECT h FROM MealPlan h WHERE h.user = ?1")
    List<MealPlan> findAllByUser(User user);

    @Query("select m from MealPlan m ORDER BY m.date DESC LIMIT 15" )
    List<Object[]> findAllTop15ByDateDESC();

    @Query("SELECT m FROM MealPlan m WHERE DATE(m.date) = DATE(:date)")
    Object[] findAllByDate(@Param("date") Date date);

    @Query("SELECT m FROM MealPlan m WHERE m.user = ?1 AND DATE(m.date) = DATE(?2)")
    List<MealPlan> findAllByUserAndDate(User user, Timestamp date);

    @Query("SELECT COUNT(m) FROM MealPlan m WHERE DATE(m.date) = DATE(:date)")
    Long countByDate(@Param("date") Date date);




}
