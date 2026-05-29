package com.Spring.Cinetime.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UsuarioRequestDTO(

        @NotBlank(message = "Nome de usuário é obrigatório")
        String name,

        @Email
        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotNull(message = "Data é obrigatória")
        LocalDate date,

        @NotBlank(message = "Senha é obrigatória")
        String senha,

        @NotBlank(message = "Confirmação da senha é obrigatória")
        String confirmarSenha,

        String biografia,
        String fotoPerfil
) {}