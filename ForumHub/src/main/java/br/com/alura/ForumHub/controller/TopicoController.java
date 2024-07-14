package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.controller.dominio.topico.GerenciadorTopicoService;
import br.com.alura.ForumHub.controller.dominio.topico.TopicoRepository;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosAtualizacaoTopicoDTO;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosDetalhamentoTopicoDto;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosCadastroTopicoDto;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosExclusaoTopicoDto;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosExclusaoTopicoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    GerenciadorTopicoService topicosGerenciador;
    @Autowired
    TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCadastroTopicoDto dados) {
        var topico = topicosGerenciador.criarTopico(dados);

        return ResponseEntity.ok(topico);

    }

    @GetMapping
    public ResponseEntity listarTopicos(){
        var topicos = repository.findAll().stream()
                .map(DadosDetalhamentoTopicoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, @RequestBody @Valid DadosExclusaoTopicoDto dados) {
        topicosGerenciador.excluir(dados, id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopicoDTO dados) {
        var topico = repository.getReferenceById(id);
        topico.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopicoDto(topico));
    }
}
