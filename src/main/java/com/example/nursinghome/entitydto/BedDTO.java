package com.example.nursinghome.entitydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BedDTO {
    private String name;
    private Long roomIdFk;
}
