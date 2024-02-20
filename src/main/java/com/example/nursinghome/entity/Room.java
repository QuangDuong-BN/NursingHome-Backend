package com.example.nursinghome.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true, columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String name;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "max_people", nullable = true)
    private Integer maxPeople;

    @Column(name = "current_people", nullable = true)
    private Integer currentPeople;

    @Column(name = "list_id_user", nullable = true, columnDefinition = "JSON")
    private String listIdUser;

    @Column(name = "describe_room", nullable = true, columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String description;

    // Getters and setters
}