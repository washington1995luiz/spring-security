package br.com.washington.springsecurity.controllers;

import br.com.washington.springsecurity.dto.request.LoginRequestDTO;
import br.com.washington.springsecurity.dto.request.SignUpRequestDTO;
import br.com.washington.springsecurity.dto.response.UserResponseDTO;
import br.com.washington.springsecurity.entities.User;
import br.com.washington.springsecurity.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authenticate")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO login) {
        return authService.login(login);
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO signup(@Valid @RequestBody SignUpRequestDTO signup) {
        return authService.signup(signup);
    }

    @GetMapping("/{name}")
    public User findByName(@PathVariable(value = "name") String name) {
        return authService.findByName(name);
    }
}
