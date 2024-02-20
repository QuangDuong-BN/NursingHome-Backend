package com.example.nursinghome.entitydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HealthRecordDTO {
    private Long userId; // or UserDTO user; if you need more user details
    private Double weight;
    private Integer bloodPressure;
    private Integer heartbeat;
    private Double temperature;
    private String awareness;
    private String mood;
    private Timestamp date;
    private String note;
}
