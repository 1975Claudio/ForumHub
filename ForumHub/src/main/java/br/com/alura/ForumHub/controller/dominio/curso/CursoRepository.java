package br.com.alura.ForumHub.controller.dominio.curso;

import br.com.alura.ForumHub.controller.dominio.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
