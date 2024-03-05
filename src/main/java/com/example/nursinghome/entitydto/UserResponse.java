package com.example.nursinghome.entitydto;

import com.example.nursinghome.enumcustom.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private String name;
    private String phone;
    private RoleUser role;
    private String email;
}
