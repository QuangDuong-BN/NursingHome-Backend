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
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Column(name = "date_of_action")
    private Date dateOfAction;

    @Column(name = "is_visitable")
    private Boolean isVisitable;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}