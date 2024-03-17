package com.example.nursinghome.entitydto;

import com.example.nursinghome.enumcustom.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceRecordDTO {
    private Long userIdFk;
    private Long serviceInfoIdFk;
    private Long bedIdFk;
    private Timestamp productionDate;
    private Timestamp expirationDate;
    private RoomType roomType;
}
