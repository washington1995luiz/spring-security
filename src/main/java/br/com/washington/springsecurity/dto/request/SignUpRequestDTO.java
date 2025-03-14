package br.com.washington.springsecurity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SignUpRequestDTO
        (
                @NotNull @NotBlank @NotEmpty
                String username,
                @NotNull @NotBlank @NotEmpty
                String password,
                @NotNull @NotBlank @NotEmpty
                String role
        ) {
}
