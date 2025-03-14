package br.com.washington.springsecurity.services;

import br.com.washington.springsecurity.dto.request.LoginRequestDTO;
import br.com.washington.springsecurity.dto.request.SignUpRequestDTO;
import br.com.washington.springsecurity.dto.response.UserResponseDTO;
import br.com.washington.springsecurity.entities.User;

public interface AuthService {

    UserResponseDTO login(LoginRequestDTO login);

    UserResponseDTO signup(SignUpRequestDTO signup);

    User findByName(String name);
}
