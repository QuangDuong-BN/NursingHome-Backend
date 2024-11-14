package com.example.nursinghome.entitydto;

import com.example.nursinghome.constants.enums.TimeOfDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VisitRecordDTO {
    private Long visitedId;
    private TimeOfDay timeOfDay;
    private Date visitDate;
}
