/* (C)2024 */
package com.example.nursinghome.auth;

import com.example.nursinghome.constants.enums.GenderUser;
import com.example.nursinghome.constants.enums.RoleUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;

    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
    private String username;

    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @Email(message = "Email is invalid")
    private String email;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String address;
    private RoleUser role;
    private GenderUser gender;
    private MultipartFile imageFile;
}
