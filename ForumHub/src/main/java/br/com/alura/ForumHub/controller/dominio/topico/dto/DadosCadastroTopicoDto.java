package br.com.alura.ForumHub.controller.dominio.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopicoDto(
        @NotNull
        Long usuario_id,

        @NotBlank
        String senha,

        @NotNull
        Long id,

        String titulo) {

}



