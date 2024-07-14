package br.com.alura.ForumHub.controller.dominio.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoUsuarioDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {}



