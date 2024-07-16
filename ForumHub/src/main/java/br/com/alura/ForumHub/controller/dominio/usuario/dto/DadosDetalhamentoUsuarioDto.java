package br.com.alura.ForumHub.controller.dominio.usuario.dto;

import br.com.alura.ForumHub.controller.dominio.usuario.Usuario;


public record DadosDetalhamentoUsuarioDto(
        Long id,
        String nome,
        String email
) {
    public DadosDetalhamentoUsuarioDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

   }





