package com.Spring.Cinetime.Dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Email invalido") String email,
        @NotBlank(message = "Senha invalida") String senha
) {
}
