package br.com.washington.springsecurity.services.impl;

import br.com.washington.springsecurity.dto.request.LoginRequestDTO;
import br.com.washington.springsecurity.dto.request.SignUpRequestDTO;
import br.com.washington.springsecurity.dto.response.UserResponseDTO;
import br.com.washington.springsecurity.entities.Roles;
import br.com.washington.springsecurity.entities.User;
import br.com.washington.springsecurity.enums.RoleName;
import br.com.washington.springsecurity.exception.InvalidRoleValuePassedException;
import br.com.washington.springsecurity.exception.UserAlreadyExistsException;
import br.com.washington.springsecurity.exception.UserNotFoundException;
import br.com.washington.springsecurity.repositoy.UserRepository;
import br.com.washington.springsecurity.security.AuthenticationService;
import br.com.washington.springsecurity.services.AuthService;
import br.com.washington.springsecurity.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final RolesService rolesService;

    @Override
    public UserResponseDTO login(LoginRequestDTO login) {
        Authentication authenticate = authenticationService.authentication(login.username(), login.password());
        User user = userRepository.findByUsername(login.username()).orElseThrow(UserNotFoundException::new);
        String token = authenticationService.generateToken(authenticate);
        return new UserResponseDTO(user.getUsername(), token);
    }

    @Transactional
    @Override
    public UserResponseDTO signup(SignUpRequestDTO signup) {

        if (userRepository.findByUsername(signup.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        RoleName roleName = getRoleName(signup.role());

        Set<Roles> roles = rolesService.setRoles(roleName.name());

        User user = builder(signup, roles);

        User saved = userRepository.save(user);
        Authentication authentication = authenticationService.authentication(saved.getUsername(), signup.password());

        String token = authenticationService.generateToken(authentication);
        return new UserResponseDTO(saved.getUsername(), token);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByUsername(name).orElseThrow(UserNotFoundException::new);
    }

    private User builder(SignUpRequestDTO signup, Set<Roles> roles) {
        return User.builder()
                .username(signup.username())
                .password(passwordEncoder.encode(signup.password()))
                .roles(roles)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    private RoleName getRoleName(String role) {
        try {
            return RoleName.valueOf(role.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleValuePassedException(role);
        }
    }
}
