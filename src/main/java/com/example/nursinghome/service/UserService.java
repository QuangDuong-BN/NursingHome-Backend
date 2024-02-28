package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public List<User> getAllUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token

        System.out.println(username);
        if (username.compareTo("ADMIN") ==0){
            return userRepository.findAll();
        }
        throw  new RoleException("You don't have permission an admin");
    }
    public User getUserByID(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token

        if (username.compareTo("ADMIN") ==0){
            return userRepository.findByUsername(username).orElse(null);
        }
        else {
            return userRepository.findByUsername(username).orElse(null);
        }
    }
    public void updateUser() {
    }
    public void deleteUser() {
    }
}
