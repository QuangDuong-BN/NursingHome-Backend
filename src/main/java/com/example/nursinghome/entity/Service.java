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
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "production_date", nullable = false)
    private Date productionDate;

    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "describe_service", nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    // Getters and setters
}
