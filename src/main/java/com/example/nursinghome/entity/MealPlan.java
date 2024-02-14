package com.example.nursinghome.entity;
import jakarta.persistence.*;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_plan")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @Column(name = "breakfast", nullable = false, columnDefinition = "JSON")
    private String breakfast;

    @Column(name = "lunch", nullable = false, columnDefinition = "JSON")
    private String lunch;

    @Column(name = "dinner", nullable = false, columnDefinition = "JSON")
    private String dinner;

    @Column(name = "date", nullable = false,columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "note", nullable = false, columnDefinition = "LONGTEXT")
    private String note;

    // Getters and setters
}