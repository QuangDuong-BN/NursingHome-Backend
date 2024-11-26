/* (C)2024 */
package com.example.nursinghome.auth;

import com.example.nursinghome.config.JwtService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request) throws JOSEException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) throws JOSEException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(HttpServletRequest httpServletRequest,
                                            @RequestParam("password") String password) throws JOSEException {
        String token = jwtService.getTokenFromHttpServletRequest(httpServletRequest);
        String username = jwtService.extractUsername(token);
        return ResponseEntity.ok(authenticationService.changePassword(username, password));
    }

}
