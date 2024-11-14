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
public class ActionDTO {
    private Long userId;
    private String name;
    private TimeOfDay timeOfDay;
    private Date dateOfAction;
    private Boolean isVisitable;
    private String description;
}
