package com.example.nursinghome.entitydto;

import com.example.nursinghome.enumcustom.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDTO {
    private String name;
    private RoomType roomType;
    private String description;
}
