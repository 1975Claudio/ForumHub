package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.controller.dominio.resposta.GerenciadoRespostaService;
import br.com.alura.ForumHub.controller.dominio.resposta.GerenciadoRespostaService;
import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosCriacaoRespostaDto;
import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosExclusaoRespostaDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    GerenciadoRespostaService respostaGen;

    @PostMapping
    @Transactional
    public ResponseEntity criarResposta(@RequestBody @Valid DadosCriacaoRespostaDto dados) {
        var resposta = respostaGen.criarResposta(dados);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirResposta(@PathVariable Long id, @RequestBody @Valid DadosExclusaoRespostaDto dados) {
        respostaGen.excluir(dados, id);

        return ResponseEntity.noContent().build();
    }
}
