package br.com.washington.springsecurity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotNull @NotBlank @NotEmpty
        String username,
        @NotNull @NotBlank @NotEmpty
        String password
) {
}
