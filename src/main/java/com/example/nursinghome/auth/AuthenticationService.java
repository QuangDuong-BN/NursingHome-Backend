package com.example.nursinghome.auth;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.User;
import com.example.nursinghome.constants.enums.RoleUser;
import com.example.nursinghome.exception.EmailAlreadyExistException;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.UserRepository;
import com.example.nursinghome.repository.httpclient.MailClient;
import com.example.nursinghome.service.RedisService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailClient mailClient;
    private final RedisService redisService;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String BLACKLIST_PREFIX = "blacklist:token:";
    private static final long BLACKLIST_EXPIRATION_MINUTES = 30;

    public AuthenticationResponse register(RegisterRequest request) throws JOSEException {
        if (userRepository.countByEmail(request.getEmail()) > 0 ||
                userRepository.countByUsername(request.getUsername()) > 0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
        // xac dinh role
        if (Objects.equals(request.getRole(), "ADMIN")) {
            throw new RoleException("You don't have permission to register as an admin");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phone(request.getPhone())
                .tokenVersion(0)
                .build();

        var jwtToken = jwtService.generateTokenWithNumBus(user);
        user = userRepository.save(user);

        redisTemplate.opsForValue().set("user:token_version:" + user.getUsername(), 0);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(null)
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        //this authentication manager take an object of type username and password authentication token
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );// if the username and password is not correct the exception is thrown
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateTokenWithNumBus(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .id(null)
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .phone(user.getPhone())
                .role(user.getRole())
                .address(user.getAddress())
                .gender(user.getGender())
                .imageUrl(user.getImageUrl())
                .build();
        return authenticationResponse;
    }


    public AuthenticationResponse registerForFamilyMember(RegisterRequest request) throws JOSEException {
        if (userRepository.countByEmail(request.getEmail()) > 0 ||
                userRepository.countByUsername(request.getUsername()) > 0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
        // xac dinh role
        RoleUser role = request.getRole();
        if (RoleUser.ADMIN != role && RoleUser.FAMILY_MEMBER != role) {
            throw new RoleException("You don't have permission to register as an ADMIN or FAMILY_MEMBER");
        }

        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .role(RoleUser.SERVICE_USER)
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateTokenWithNumBus(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(null)
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    public String changePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        Integer tokenVersion = user.getTokenVersion();
        tokenVersion++;
        user.setTokenVersion(tokenVersion);
        redisTemplate.opsForValue().set("user:token_version:" + user.getUsername(), tokenVersion);
        userRepository.save(user);
        return "Password changed successfully";
    }

    public String logOut(String token) {
        String key = BLACKLIST_PREFIX + token;
        redisTemplate.opsForValue().set(key, "blacklisted", BLACKLIST_EXPIRATION_MINUTES, TimeUnit.DAYS);
        return "Log out successfully";
    }
    // Kiểm tra token có trong blacklist không
    public boolean isTokenBlacklisted(String token) {
        String key = BLACKLIST_PREFIX + token;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}


