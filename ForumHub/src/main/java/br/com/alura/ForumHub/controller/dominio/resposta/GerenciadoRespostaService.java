package br.com.alura.ForumHub.controller.dominio.resposta;

import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosCriacaoRespostaDto;
import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosDetalhamentoRespostaDto;
import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosExclusaoRespostaDto;
import br.com.alura.ForumHub.controller.dominio.topico.TopicoRepository;
import br.com.alura.ForumHub.controller.dominio.usuario.UsuarioRepository;
import br.com.alura.ForumHub.infra.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadoRespostaService {

    @Autowired
    RespostaRepository repository;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    TopicoRepository topicoRepository;


    public DadosDetalhamentoRespostaDto criarResposta(DadosCriacaoRespostaDto dados) {

        if (!topicoRepository.existsById(dados.topico_id())) {
            throw new ValidacaoException("O tópico informado não existe!");
        }
        if (!userRepository.existsById(dados.usuario_id())) {
            throw new ValidacaoException("O usuário informado não existe!");
        }

        var usuario = userRepository.getReferenceById(dados.usuario_id());
        var topico = topicoRepository.getReferenceById(dados.topico_id());

        var resposta = new Resposta(topico, usuario, dados);

        repository.save(resposta);
        return new DadosDetalhamentoRespostaDto(resposta);
    }

    public String excluir(DadosExclusaoRespostaDto dados, Long id) {
        Optional<Resposta> resposta = repository.findById(id);
        if (resposta.isEmpty()) {
            throw new ValidacaoException("Esta resposta não pôde ser deletada, pois não existe!");
        }
        if (!resposta.get().getUsuario().getEmail().equals(dados.email()) && !resposta.get().getTopico().getUsuario().getEmail().equals(dados.senha())) {
            throw new ValidacaoException("Você só pode excluir respostas de sua autoria, a menos que seja o criador do tópico.");
        }

        repository.delete(resposta.get());

        return "Resposta: " + resposta.get() + " deletada com sucesso.";
    }
}

