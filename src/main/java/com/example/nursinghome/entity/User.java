package com.example.nursinghome.entity;

import com.example.nursinghome.enumcustom.GenderUser;
import com.example.nursinghome.enumcustom.RecordStatus;
import com.example.nursinghome.enumcustom.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "family_member_id_fk")
    private User familyMember; // Khóa ngoại tham chiếu đến người nhà quản lý dịch vụ người dùng

    @Column(name = "name", nullable = false, columnDefinition = "VarChar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleUser role;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderUser gender;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RecordStatus status;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
