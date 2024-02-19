package com.example.nursinghome.entity;
import jakarta.persistence.*;

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
@Table(name = "health_record")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;
    // can nang
    @Column(name = "weigh", nullable = true)
    private Double weight;
    // huyet ap
    @Column(name = "blood_pressure", nullable = true)
    private Integer bloodPressure;
    // nhip tim
    @Column(name = "heartbeat", nullable = true)
    private Integer heartbeat;
    // nhiet do
    @Column(name = "temperature", nullable = true)
    private Double temperature;
    // nhan thuc, tam trang, ngay, ghi chu
    @Column(name = "awareness", nullable = true)
    private String awareness;
    // tam trang
    @Column(name = "mood", nullable = true)
    private String mood;
    // ngay
    @Column(name = "date", nullable = true)
    private Date date;
    // ghi chu
    @Column(name = "note", nullable = false, columnDefinition = "LONGTEXT")
    private String note;

    // Getters and setters
}