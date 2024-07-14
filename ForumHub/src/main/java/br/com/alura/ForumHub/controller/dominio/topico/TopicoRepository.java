package br.com.alura.ForumHub.controller.dominio.topico;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findById(Long topicoId);
}
