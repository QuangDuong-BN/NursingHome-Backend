package com.example.nursinghome.entity;

import com.example.nursinghome.enumcustom.TimeOfDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Getters and setters
}