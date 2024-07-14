package br.com.alura.ForumHub.controller.dominio.curso.Dto;

import br.com.alura.ForumHub.controller.dominio.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCursoDto(

        @NotBlank
        String nome,

        @NotNull
        Categoria categoria
) {}
