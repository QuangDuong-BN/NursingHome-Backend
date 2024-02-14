package com.example.nursinghome.myapplicationrunner;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Configuration
public class MyApplicationRunner implements ApplicationRunner {
    @Value("${server.port}")
    private Integer port;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(port);
    }
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("Công việc đã được thực hiện khi ứng dụng khởi động:");
//        System.out.println("- Check và tạo admin nếu chưa được tạo.");
//        Optional<User> numberOfAdmin = userRepository.findByUsername("ADMIN");
//        if (numberOfAdmin.isEmpty()) {
//            var user = User.builder()
//                    .name("ADMIN")
//                    .email("ADMIN.gmail.com")
//                    .username("ADMIN")
//                    .password(passwordEncoder.encode("ADMIN"))
//                    .role(RoleUser.ADMIN)
//                    .phone("123456789")
//                    .storageId(null)
//                    .marketId(null)
//                    .build();
//            userRepository.save(user);
//        }
//
//    }
}