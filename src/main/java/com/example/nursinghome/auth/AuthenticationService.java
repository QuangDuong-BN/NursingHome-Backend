package com.example.nursinghome.auth;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.User;
import com.example.nursinghome.enumcustom.RoleUser;
import com.example.nursinghome.exception.EmailAlreadyExistException;
import com.example.nursinghome.exception.RoleException;
import com.example.nursinghome.repository.UserRepository;
import com.example.nursinghome.repository.httpclient.MailClient;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailClient mailClient;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.countByEmail(request.getEmail()) > 0 ||
                userRepository.countByUsername(request.getUsername()) > 0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
        // xac dinh role
        RoleUser role;
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
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        // send mail to user after register successfully
        log.info("- Gui mail");
        mailClient.sendEmail();

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


    public AuthenticationResponse registerForFamilyMember(RegisterRequest request) {
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
        var jwtToken = jwtService.generateToken(user);
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
}


