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
public class CostsIncurredDTO {
    private Long userId;
    private Integer quantity;
    private Double price;
    private Timestamp date;
    private String note;
}
