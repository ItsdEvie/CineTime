package com.Spring.Cinetime.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;


public record UsuarioRequestDTO(

            @NotBlank(message = "Nome De usuario é Obrigatorio.")
            String name,

            @NotNull(message = "Email é obrigatorio")
            String email,

            @NotNull(message = "Data é obrigatorio")
            Date date,

            @Positive@NotNull(message = "Senha é obrigatorio")
            String senha,

            @NotBlank@NotNull(message = "Senha é obrigatorio")
            String confirmarSenha
) {}

