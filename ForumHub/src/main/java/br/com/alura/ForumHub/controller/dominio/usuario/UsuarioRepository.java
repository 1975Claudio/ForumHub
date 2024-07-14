package br.com.alura.ForumHub.controller.dominio.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String subject);

    List<Usuario> findAllByAtivoTrue();
}
