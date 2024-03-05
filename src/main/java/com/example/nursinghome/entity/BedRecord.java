package com.example.nursinghome.entity;

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
@Table(name = "bed_record")
public class BedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "bed_id_fk", nullable = false)
    private Bed bedIdFk;

    @OneToOne()
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User userIdFk;

    @Column(name = "production_date", nullable = false)
    private Timestamp productionDate;

    @Column(name = "expiration_date", nullable = false)
    private Timestamp expirationDate;

}
