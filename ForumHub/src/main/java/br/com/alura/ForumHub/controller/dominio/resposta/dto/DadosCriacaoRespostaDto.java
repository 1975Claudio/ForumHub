package br.com.alura.ForumHub.controller.dominio.resposta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriacaoRespostaDto(
        @NotBlank
        String mensagem,

        @NotNull
        Long topico_id,

        @NotNull
        Long usuario_id
) {}
