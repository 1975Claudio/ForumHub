package br.com.alura.ForumHub.controller.dominio.resposta.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoRespostaDto(
        @NotBlank
        String email,

        @NotBlank
        String senha
) {}
