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
@Table(name = "health_record")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @Column(name = "weigh", nullable = false)
    private Double weight;

    @Column(name = "blood_pressure", nullable = false)
    private Integer bloodPressure;

    @Column(name = "heartbeat", nullable = false)
    private Integer heartbeat;

    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @Column(name = "awareness", nullable = false)
    private String awareness;

    @Column(name = "mood", nullable = false)
    private String mood;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "note", nullable = false, columnDefinition = "LONGTEXT")
    private String note;

    // Getters and setters
}