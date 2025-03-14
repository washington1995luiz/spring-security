package br.com.washington.springsecurity.dto.response;

public record UserResponseDTO(
        String username,
        String token
) {
}
