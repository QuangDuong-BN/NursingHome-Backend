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
public class MealPlanDTO {
    private Long userId; // or UserDTO user; if you need more user details
    private String breakfast;
    private String lunch;
    private String dinner;
    private Timestamp date;
    private String note;
}
