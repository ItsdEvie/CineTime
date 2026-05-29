package com.Spring.Cinetime.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UsuarioUpdateDTO(

        @NotBlank(message = "Nome de usuário é obrigatório")
        String name,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        LocalDate date,

        String senha,

        String confirmarSenha,

        String biografia,

        String fotoPerfil

) {
}