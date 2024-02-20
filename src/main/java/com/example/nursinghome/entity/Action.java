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
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", nullable = false)
    private User user;

//    @Column(name = "date", nullable = true)
//    private Timestamp date;

    @Column(name = "to_do_list", nullable = true, columnDefinition = "TEXT")
    private String toDoList;

    // Getters and setters
}