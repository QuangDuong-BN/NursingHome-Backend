package com.example.nursinghome.entitydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HealthRecordDTO {
    public Long userId; // or UserDTO user; if you need more user details
    public String address; // or UserDTO user; if you need more user details
    public String name; // or UserDTO user; if you need more user details
    public Double weight;
    public String image_url;
    public Integer bloodPressure;
    public Integer heartbeat;
    public Double temperature;
    public String awareness;
    public String mood;
    public Timestamp date;
    public String note;
}
