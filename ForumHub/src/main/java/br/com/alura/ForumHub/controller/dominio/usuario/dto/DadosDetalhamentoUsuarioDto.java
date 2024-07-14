package br.com.alura.ForumHub.controller.dominio.usuario.dto;

import br.com.alura.ForumHub.controller.dominio.usuario.Usuario;


public record DadosDetalhamentoUsuariosDto(
        Long id,
        String nome,
        String email;
) {
    public DadosDetalhamentoUsuariosDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}





