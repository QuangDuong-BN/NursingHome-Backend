package com.example.nursinghome.entitydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserStaffAssignmentDTO {
    public Long userId;
    public Long staffId;
    public Long serviceRecordId;
}
