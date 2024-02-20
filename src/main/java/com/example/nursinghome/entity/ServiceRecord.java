package com.example.nursinghome.entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "service_record")
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_info_id_fk", nullable = false)
    private ServiceInfo serviceInfo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "production_date", nullable = false)
    private Timestamp productionDate;

    @Column(name = "expiration_date", nullable = false)
    private Timestamp expirationDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "describe_service", nullable = true, columnDefinition = "LONGTEXT")
    private String description;

    // Getters and setters
}
