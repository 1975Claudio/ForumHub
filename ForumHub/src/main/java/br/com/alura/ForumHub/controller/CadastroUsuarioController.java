package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.controller.dominio.usuario.Usuario;
import br.com.alura.ForumHub.controller.dominio.usuario.UsuarioRepository;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosCadastroUsuariosDto;
import br.com.alura.ForumHub.controller.dominio.usuario.dto.DadosDetalhamentoUsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastro_usuario")
public class CadastroUsuarioController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuariosDto dados, UriComponentsBuilder uriBuilder) {

        var usuario = new Usuario(dados);

        repository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuarioDto(usuario));
    }
}
