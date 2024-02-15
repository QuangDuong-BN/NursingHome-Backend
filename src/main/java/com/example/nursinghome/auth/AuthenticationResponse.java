package com.example.nursinghome.auth;


import com.example.nursinghome.enumCustom.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private RoleUser role;
}
