package br.com.alura.ForumHub.controller.dominio.topico;

import br.com.alura.ForumHub.controller.dominio.curso.CursoRepository;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosCadastroTopicoDto;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosDetalhamentoTopicoDto;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosExclusaoTopicoDto;
import br.com.alura.ForumHub.controller.dominio.usuario.UsuarioRepository;
import br.com.alura.ForumHub.infra.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadorTopicoService {

    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    UsuarioRepository userRepository;
    @Autowired
    TopicoRepository topicoRepository;

    public DadosDetalhamentoTopicoDto criarTopico(DadosCadastroTopicoDto dados) {

        if (!cursoRepository.existsById(dados.curso_id())) {
            throw new ValidacaoException("Curso não existe no sistema!");
        }

        if (!userRepository.existsById(dados.usuario_id())) {
            throw new ValidacaoException("Usuario não existe!");
        }

        var usuario = userRepository.getReferenceById(dados.usuario_id());
        var curso = cursoRepository.getReferenceById(dados.curso_id());

        var topico = new Topico(usuario, curso, dados);

        topicoRepository.save(topico);
        return new DadosDetalhamentoTopicoDto(topico);
    }

    public String excluir(DadosExclusaoTopicoDto dados, Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            throw new ValidacaoException("Nenhum tópico encontrado com o id informado.");
        }
        if (!(topico.get().getUsuario().getEmail().equals(dados.email())) && !topico.get().getUsuario().getSenha().equals(dados.senha())) {
            throw new ValidacaoException("Não é possível excluir tópicos que não são seus!");
        } else {
            topicoRepository.delete(topico.get());
            return "Tópico " + topico.get() + " excluído!";
        }
    }
}


