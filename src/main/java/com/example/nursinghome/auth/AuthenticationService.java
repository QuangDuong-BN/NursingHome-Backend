package com.example.nursinghome.auth;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.enumCustom.RoleUser;
import com.example.nursinghome.exception.EmailAlreadyExistException;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.countByEmail(request.getEmail()) > 0 ||
                userRepository.countByUsername(request.getUsername()) >0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
        // xac dinh role
        RoleUser role;
        if (Objects.equals(request.getRole(), "ADMIN")){
            throw  new RoleException("You don't have permission to register as an admin");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleUser.valueOf(request.getRole()))
                .phone(request.getPhone())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(      //this authentication manager take an object of type username and password authentication token
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );// if the username and password is not correct the exception is thrown
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
        return authenticationResponse;
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
    }
}


