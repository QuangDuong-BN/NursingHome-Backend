package com.example.nursinghome.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nursinghome.auth.AuthenticationResponse;
import com.example.nursinghome.auth.RegisterRequest;
import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.enumcustom.RoleUser;
import com.example.nursinghome.exception.EmailAlreadyExistException;
import com.example.nursinghome.exception.NotImplementedException;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.projectioninterface.UserProjection;
import com.example.nursinghome.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    public AuthenticationResponse registerForFamilyMember(HttpServletRequest httpServletRequest, RegisterRequest registerRequest) throws IOException {

        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token

        if (userRepository.countByEmail(registerRequest.getEmail()) > 0 ||
                userRepository.countByUsername(registerRequest.getUsername()) > 0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }

        String randomString;
        // random username
        while (true) {
            int length = 12;
            randomString = generateRandomString(length);
            if (userRepository.countByUsername(randomString) == 0) break;
        }
        // xac dinh role
        RoleUser role = userRepository.getUerByUserName(username).getRole();
        if (RoleUser.ADMIN == role || RoleUser.FAMILY_MEMBER == role) {
            User familyMember = userRepository.getUerByUserName(username);
            var user = User.builder()
                    .name(registerRequest.getName())
                    .username(randomString)
                    .role(RoleUser.SERVICE_USER)
                    .dateOfBirth(registerRequest.getDateOfBirth())
                    .address(registerRequest.getAddress())
                    .gender(registerRequest.getGender())
                    .imageUrl(null)
                    .familyMember(familyMember)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .name(registerRequest.getName())
                    .username(randomString)
                    .role(RoleUser.SERVICE_USER)
                    .dateOfBirth(registerRequest.getDateOfBirth())
                    .address(registerRequest.getAddress())
                    .gender(registerRequest.getGender())
                    .imageUrl(null)
                    .build();
        } else throw new RoleException("You don't have permission to register as an ADMIN or FAMILY_MEMBER");
    }

    public List<User> getAllUser(HttpServletRequest request) {
        return userRepository.findAllUserAndFamilyUser();
    }

    public User getUser(HttpServletRequest request) {
        String username = jwtService.extractUsername(SecurityContextHolder.getContext().getAuthentication().getName()); // Sử dụng JwtService để lấy username từ token
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> findAllByIDOrName(HttpServletRequest request, Long id, String name) {
        String token = request.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
        return userRepository.findAllByIDOrName(id, name);
    }


    public Object getUserById(HttpServletRequest request, Long id) {
        return userRepository.getNameDateAddressGenderByID(id);
    }

    public List<UserProjection> getListUserByFamilyMember(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
        User familyMember = userRepository.getUerByUserName(username);
        return userRepository.getAllUserByFamilyMember(familyMember);
    }

    public User updateUser(HttpServletRequest httpServletRequest, User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));

        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setAddress(user.getAddress());
        existingUser.setGender(user.getGender());
        existingUser.setImageUrl(user.getImageUrl());

        return userRepository.save(existingUser);
    }

    public void deleteUser(HttpServletRequest httpServletRequest, Long id) {
        userRepository.deleteById(id);
    }

    public String uploadImageForUser(HttpServletRequest httpServletRequest, Long Id, MultipartFile imageFile) throws IOException {
        // Tạo đối tượng Cloudinary
        Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
        Map<?, ?> result = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
        // Lấy URL của ảnh tải lên từ kết quả
        String imageUrl = (String) result.get("url");
        User user = userRepository.findById(Id).orElse(null);
        if (user != null) {
            user.setImageUrl(imageUrl);
            userRepository.save(user);
            return imageUrl;
        }
        throw new NotImplementedException("User not found");
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
