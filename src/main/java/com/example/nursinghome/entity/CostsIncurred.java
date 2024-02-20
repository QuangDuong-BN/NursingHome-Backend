package com.example.nursinghome.entity;

import java.sql.Timestamp;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "costs_incurred")
public class CostsIncurred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @Column(name = "date", nullable = true)
    private Timestamp date;

    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "note", nullable = true, columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String note;

}
