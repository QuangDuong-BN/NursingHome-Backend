package com.example.nursinghome.myapplicationrunner;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.enumcustom.RoleUser;
import com.example.nursinghome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Configuration
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Công việc đã được thực hiện khi ứng dụng khởi động:");
        System.out.println("- Check và tạo admin nếu chưa được tạo.");
        Optional<User> numberOfAdmin = userRepository.findByUsername("ADMIN");
        if (numberOfAdmin.isEmpty()) {
            var user = User.builder()
                    .name("ADMIN")
                    .email("ADMIN.gmail.com")
                    .username("ADMIN")
                    .password(passwordEncoder.encode("ADMIN"))
                    .role(RoleUser.ADMIN)
                    .phone("123456789")
                    .build();
            userRepository.save(user);
        }

    }
}