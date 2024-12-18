package com.example.nursinghome.auth;


import com.example.nursinghome.constants.enums.GenderUser;
import com.example.nursinghome.constants.enums.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Long id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String phone;
    private Date dateOfBirth;
    private String address;
    private RoleUser role;
    private GenderUser gender;
    private String imageUrl;
}
