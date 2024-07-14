package br.com.alura.ForumHub.controller.dominio.topico.dto;

import br.com.alura.ForumHub.controller.dominio.topico.TopicoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopicoDTO(
        @NotNull
        Long usuario_id,

        @NotBlank
        String senha,

        @NotNull
        Long id,

        String titulo,

        String mensagem,

        TopicoStatus status,

        Long curso_id
) {}

