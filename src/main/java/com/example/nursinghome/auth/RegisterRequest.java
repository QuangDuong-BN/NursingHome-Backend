package com.example.nursinghome.auth;

import com.example.nursinghome.enumcustom.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Timestamp dateOfBirth;
    private String address;
    private RoleUser role;
}
