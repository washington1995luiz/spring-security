package br.com.washington.springsecurity.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public Authentication authentication(@NotNull @NotBlank String username, @NotNull @NotBlank String password) {
        return this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }

    public String generateToken(Authentication authentication) {
        return service.generateToken(authentication);
    }
}
