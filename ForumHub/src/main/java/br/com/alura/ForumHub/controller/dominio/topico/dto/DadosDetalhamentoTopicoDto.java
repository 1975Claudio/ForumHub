package br.com.alura.ForumHub.controller.dominio.topico.dto;

import br.com.alura.ForumHub.controller.dominio.curso.Dto.DadosDetalhamentoCursoDto;
import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosDetalhamentoRespostaDto;
import br.com.alura.ForumHub.controller.dominio.topico.Topico;
import br.com.alura.ForumHub.controller.dominio.topico.TopicoStatus;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosDetalhamentoUsuarioDto;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoTopicoDto(
        Long id,
        String titulo,
        String mensagem,
        OffsetDateTime data,
        TopicoStatus status,
        DadosDetalhamentoUsuarioDto autor,
        DadosDetalhamentoCursoDto curso,
        List<DadosDetalhamentoRespostaDto> respostas) {
    public DadosDetalhamentoTopicoDto(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), new DadosDetalhamentoUsuarioDto(topico.getUsuario()), new DadosDetalhamentoCursoDto(topico.getCurso()),
                topico.getRespostas().stream()
                        .map(dr -> new DadosDetalhamentoRespostaDto(dr.getId(), dr.getMensagem(), dr.getTopico().getId(),
                                dr.getData(), new DadosDetalhamentoUsuarioDto(dr.getUsuario()))).collect(Collectors.toList()));
    }
}
