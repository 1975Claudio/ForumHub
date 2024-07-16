package br.com.alura.ForumHub.controller.dominio.resposta.dto;

import br.com.alura.ForumHub.controller.dominio.resposta.Resposta;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosDetalhamentoUsuarioDto;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosDetalhamentoUsuarioDto;

import java.time.OffsetDateTime;

public record DadosDetalhamentoRespostaDto(
        Long id,
        String mensagem,
        Long topico_id,
        OffsetDateTime data,
        DadosDetalhamentoUsuarioDto usuario
) {
    public DadosDetalhamentoRespostaDto(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico().getId(), resposta.getData(), new DadosDetalhamentoUsuarioDto(resposta.getUsuario()));
    }
}
