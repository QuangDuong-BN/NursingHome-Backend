package com.example.nursinghome.entity;

import com.example.nursinghome.enum_Custom.TypeService;
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

    @Column(name = "name", nullable = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_service", nullable = true)
    private TypeService type;

    @Column(name = "price_day", nullable = true)
    private Double priceDay;

    @Column(name = "price_week", nullable = true)
    private Double priceWeek;

    @Column(name = "price_month", nullable = true)
    private Double priceMonth;

    @Column(name = "price_year", nullable = true)
    private Double priceYear;

    @Column(name = "ticket_prices ", nullable = true)
    private Double ticketPrices;

    @Column(name = "description_service", nullable = true, columnDefinition = "TEXT")
    private String descriptionService;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;
    // Getters and setters
}