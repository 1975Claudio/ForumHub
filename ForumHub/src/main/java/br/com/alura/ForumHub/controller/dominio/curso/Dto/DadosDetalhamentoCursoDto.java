package br.com.alura.ForumHub.controller.dominio.curso.Dto;

import br.com.alura.ForumHub.controller.dominio.curso.Categoria;
import br.com.alura.ForumHub.controller.dominio.curso.Curso;

public record DadosDetalhamentoCursoDto(
        Long id,
        String nome,
        Categoria categoria
) {
    public DadosDetalhamentoCursoDto(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
