package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.controller.dominio.curso.CursoRepository;
import br.com.alura.ForumHub.controller.dominio.curso.Dto.DadosDetalhamentoCursoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository repository;

    @GetMapping
    public ResponseEntity listarCursos() {
        List<DadosDetalhamentoCursoDto> cursos = repository.findAll().stream()
                .map(curso -> new DadosDetalhamentoCursoDto(curso))
                .collect(Collectors.toList());
        System.out.println(cursos);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharCurso(@PathVariable Long id) {
        var curso = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCursoDto(curso));
    }

}
