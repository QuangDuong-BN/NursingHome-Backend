package com.example.nursinghome.myapplicationrunner;

import com.example.nursinghome.model.User;
import com.example.nursinghome.constants.enums.RoleUser;
import com.example.nursinghome.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Configuration
@AllArgsConstructor
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Công việc đã được thực hiện khi ứng dụng khởi động: Check và tạo admin nếu chưa được tạo.");

        Optional<User> numberOfAdmin = userRepository.findByUsername("admin@lotuscare.com");
        if (numberOfAdmin.isEmpty()) {
            var user = User.builder()
                    .name("ADMIN")
                    .email("admin@lotuscare.com")
                    .username("admin@lotuscare.com")
                    .password(passwordEncoder.encode("123456"))
                    .role(RoleUser.ADMIN)
                    .phone("0344381803")
                    .build();
            userRepository.save(user);
        }
    }
}