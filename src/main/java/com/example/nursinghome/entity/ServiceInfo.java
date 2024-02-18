package com.example.nursinghome.entity;

import com.example.nursinghome.enumCustom.TypeService;
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
@Table(name = "service_info")
public class ServiceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_service", nullable = false)
    private TypeService type;

    @Column(name = "price_day", nullable = false)
    private Double priceDay;

    @Column(name = "price_week", nullable = false)
    private Double priceWeek;

    @Column(name = "price_month", nullable = false)
    private Double priceMonth;

    @Column(name = "price_year", nullable = false)
    private Double priceYear;

    @Column(name = "ticket_prices ", nullable = false)
    private Double ticketPrices;

    @Column(name = "description_service", nullable = true, columnDefinition = "JSON")
    private String description_service;

    @Column(name = "image_url", nullable = true)
    private String image_url;
    // Getters and setters
}