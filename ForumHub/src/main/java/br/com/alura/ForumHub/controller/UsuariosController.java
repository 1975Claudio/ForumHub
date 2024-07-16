package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.controller.dominio.usuario.GerenciadorUsuarioService;
import br.com.alura.ForumHub.controller.dominio.usuario.UsuarioRepository;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosDetalhamentoUsuarioDto;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosExclusaoUsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    GerenciadorUsuarioService userGen;

    @GetMapping
    public ResponseEntity listarUsuarios(){
        List<DadosDetalhamentoUsuarioDto> usuarios = repository.findAllByAtivoTrue()
                .stream().map(DadosDetalhamentoUsuarioDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id, @RequestBody @Valid DadosExclusaoUsuarioDto dados){
        userGen.excluir(id, dados);
        return ResponseEntity.noContent().build();
    }
}
