package br.com.alura.ForumHub.controller.dominio.topico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoTopicoDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {}
