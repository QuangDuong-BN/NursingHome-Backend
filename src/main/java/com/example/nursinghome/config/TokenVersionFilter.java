package com.example.nursinghome.config;

import com.example.nursinghome.auth.AuthenticationService;
import com.example.nursinghome.exception.AccessDeniedException;
import com.example.nursinghome.service.RedisService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

/**
 * @author Quang Duong
 * @summary java 21
 */
@AllArgsConstructor
@Slf4j
@Component
public class TokenVersionFilter extends OncePerRequestFilter {


    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            // kiem tra token co trong black list khong
            if(authenticationService.isTokenBlacklisted(token)){
                throw new AccessDeniedException("Token is invalid");
            }
            try {
                String username = jwtService.extractUsername(token);
                int tokenVersionFromToken = jwtService.extractVersionToken(token);
                log.info("Token version from token: {}", tokenVersionFromToken);
                // Lấy token_version từ Redis
                Integer tokenVersionFromRedis = Optional.ofNullable(redisTemplate.opsForValue().get("user:token_version:" + username))
                        .map(Object::toString)
                        .map(Integer::valueOf)
                        .orElse(null);
                log.info("Token version from Redis: {}", tokenVersionFromRedis);
                // So sánh token_version
                if (tokenVersionFromToken != tokenVersionFromRedis) {
                    throw new AccessDeniedException("Token is invalid");
                }
            } catch (Exception e) {
                throw new AccessDeniedException("Token is invalid");
            }

        }
        // Nếu token hợp lệ, tiếp tục xử lý request
        filterChain.doFilter(request, response);
    }
}

