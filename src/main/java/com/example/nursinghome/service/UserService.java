package com.example.nursinghome.service;

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
    public List<User> getAllUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.println(username);
        if (username == "ADMIN"){
            return userRepository.findAll();
        }
        throw  new RoleException("You don't have permission an admin");
    }
    public User getUserByID(HttpServletRequest request) {
        String username = request.getParameter("username");
        if (username == "ADMIN"){
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
