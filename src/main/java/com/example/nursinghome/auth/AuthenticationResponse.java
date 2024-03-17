package com.example.nursinghome.auth;


import com.example.nursinghome.enumcustom.GenderUser;
import com.example.nursinghome.enumcustom.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;

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
