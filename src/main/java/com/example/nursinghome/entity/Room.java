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

    @Column(name = "name", nullable = false)
    private Long name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "max_people", nullable = false)
    private Integer maxPeople;

    @Column(name = "current_people", nullable = false)
    private Integer currentPeople;

    @Column(name = "list_id_user", nullable = false, columnDefinition = "JSON")
    private String listIdUser;

    @Column(name = "describe_room", nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    // Getters and setters
}