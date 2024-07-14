package br.com.alura.ForumHub.controller.dominio.curso;

import br.com.alura.ForumHub.controller.dominio.curso.Dto.DadosCadastroCursoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso (DadosCadastroCursoDto dados){
        this.categoria = dados.categoria();
        this.nome = dados.nome();
    }
}
