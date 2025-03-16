package br.com.washington.springsecurity.config;

import br.com.washington.springsecurity.security.JwtService;
import br.com.washington.springsecurity.services.RedisTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class JwtBlacklistFilterConfig extends OncePerRequestFilter {

    private final RedisTokenService redisTokenService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String userId = jwtService.extractUserId.apply(token);

            if (redisTokenService.isTokenRevoked(userId, token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token revoked");
                return;
            }

            String activeToken = redisTokenService.getActiveToken(userId);
            if (activeToken == null || jwtService.isExpiredToken(activeToken) || !activeToken.equals(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or replaced token");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
