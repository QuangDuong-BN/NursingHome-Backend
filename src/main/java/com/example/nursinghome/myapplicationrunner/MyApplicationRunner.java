package com.example.nursinghome.myapplicationrunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

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