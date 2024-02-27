package com.example.nursinghome.entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "meal_plan")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @Column(name = "breakfast", columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String breakfast;

    @Column(name = "lunch", columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String lunch;

    @Column(name = "dinner", columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String dinner;

    @Column(name = "date",columnDefinition = "DATETIME")
    private Timestamp date;

    @Column(name = "note", columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String note;

    // Getters and setters
}