package com.example.nursinghome.model;

import com.example.nursinghome.constants.enums.TimeOfDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "visit_record")
public class VisitRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "visitor_id_fk")
    private User visitor; // Khóa ngoại tham chiếu đến người dùng

    @ManyToOne()
    @JoinColumn(name = "visited_id_fk")
    private User visited; // Khóa ngoại tham chiếu đến người dùng

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Column(name = "visit_date")
    private Date visitDate;

}
