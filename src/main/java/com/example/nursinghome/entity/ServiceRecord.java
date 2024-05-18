package com.example.nursinghome.entity;

import com.example.nursinghome.enumcustom.PaymentStatus;
import com.example.nursinghome.enumcustom.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "service_record")
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User userIdFk;

    @ManyToOne
    @JoinColumn(name = "family_member_id_fk", nullable = false)
    private User familyMemberIdFk;

    @ManyToOne
    @JoinColumn(name = "service_info_id_fk", nullable = false)
    private ServiceInfo serviceInfoIdFk;

    @OneToOne
    @JoinColumn(name = "bed_id_fk", nullable = false)
    private Bed bedIdFk;

    @Column(name = "price", nullable = false)
    private Double price;
    //
    @Column(name = "production_date", nullable = false)
    private Timestamp productionDate;

    @Column(name = "expiration_date", nullable = false)
    private Timestamp expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, columnDefinition = "ENUM('PAID', 'UNPAID')")
    private PaymentStatus paymentStatus;

    @Column(name = "booking_time", nullable = false)
    private Timestamp bookingTime;

    @Column(name = "payment_time")
    private Timestamp paymentTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "describe_service", columnDefinition = "LONGTEXT")
    private String description;

    // Getters and setters
}
