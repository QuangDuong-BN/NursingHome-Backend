package com.example.nursinghome.entity;

import com.example.nursinghome.enumcustom.TypeService;
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

    @Column(name = "price_day")
    private Double priceDay;

    @Column(name = "price_week")
    private Double priceWeek;

    @Column(name = "price_month")
    private Double priceMonth;

    @Column(name = "price_year")
    private Double priceYear;

    @Column(name = "ticket_prices ")
    private Double ticketPrices;
    // mo ta ngan gon
    @Column(name = "description_service", columnDefinition = "TEXT")
    private String descriptionService;
    // doi tuong tham gi
    @Column(name = "participant_details", columnDefinition = "TEXT")
    private String participantDetails;
    // tien ich
    @Column(name = "amenities", columnDefinition = "TEXT")
    private String amenities;
    // che do dinh duong
    @Column(name = "nutrition_mode", columnDefinition = "TEXT")
    private String nutritionMode;
    // hoat dong cong dong
    @Column(name = "community_activities", columnDefinition = "TEXT")
    private String communityActivities;
    // che do cham soc
    @Column(name = "care_regimen", columnDefinition = "TEXT")
    private String careRegimen;
    // anh dai dien
    @Column(name = "image_url_icon")
    private String imageUrlIcon;

    @Column(name = "image_url_price")
    private String imageUrlPrice;


}