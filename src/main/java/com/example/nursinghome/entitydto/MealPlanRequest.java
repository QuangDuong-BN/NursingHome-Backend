package com.example.nursinghome.entitydto;

import com.example.nursinghome.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MealPlanRequest {
    private Long id;
    private Timestamp date;
}