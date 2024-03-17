package com.example.nursinghome.entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "health_record")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "creator_user_id_fk", nullable = false)
    private User creator;
    // can nang
    @Column(name = "weigh")
    private Double weight;
    // huyet ap
    @Column(name = "blood_pressure")
    private Integer bloodPressure;
    // nhip tim
    @Column(name = "heartbeat")
    private Integer heartbeat;
    // nhiet do
    @Column(name = "temperature")
    private Double temperature;
    // nhan thuc, tam trang, ngay, ghi chu
    @Column(name = "awareness")
    private String awareness;
    // tam trang
    @Column(name = "mood")
    private String mood;
    // ngay
    @Column(name = "date")
    private Timestamp date;
    // ghi chu
    @Column(name = "note", columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String note;

    // Getters and setters
}