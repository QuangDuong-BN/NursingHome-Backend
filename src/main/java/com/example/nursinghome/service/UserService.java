package com.example.nursinghome.service;

import com.example.nursinghome.auth.AuthenticationResponse;
import com.example.nursinghome.auth.RegisterRequest;
import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.enumcustom.RoleUser;
import com.example.nursinghome.exception.EmailAlreadyExistException;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerForFamilyMember(HttpServletRequest httpServletRequest,RegisterRequest request) {

        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token

        if (userRepository.countByEmail(request.getEmail()) > 0 ||
                userRepository.countByUsername(request.getUsername()) >0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
        // xac dinh role
        RoleUser role = request.getRole();
        if (RoleUser.ADMIN == role || RoleUser.FAMILY_MEMBER == role){
            User familyMember = userRepository.getUerByUserName(username);
            var user = User.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .role(RoleUser.SERVICE_USER)
                    .dateOfBirth(request.getDateOfBirth())
                    .address(request.getAddress())
                    .familyMember(familyMember)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .name(request.getName())
                    .username(request.getUsername())
                    .role(RoleUser.SERVICE_USER)
                    .dateOfBirth(request.getDateOfBirth())
                    .address(request.getAddress())
                    .build();
        }
        else throw  new RoleException("You don't have permission to register as an ADMIN or FAMILY_MEMBER");

    }
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

    public List<User> getListUserByFamilyMember(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
        User familyMember= userRepository.getUerByUserName(username);
        return userRepository.getAllUserByFamilyMember(familyMember);
    }
    public void updateUser() {
    }
    public void deleteUser() {
    }
}
