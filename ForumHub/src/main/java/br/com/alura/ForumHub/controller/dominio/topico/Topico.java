package br.com.alura.ForumHub.controller.dominio.topico;

import br.com.alura.ForumHub.controller.dominio.curso.Curso;
import br.com.alura.ForumHub.controller.dominio.resposta.Resposta;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosAtualizacaoTopicoDTO;
import br.com.alura.ForumHub.controller.dominio.topico.dto.DadosCadastroTopicoDto;
import br.com.alura.ForumHub.controller.dominio.usuario.Usuario;
import br.com.alura.ForumHub.infra.ValidacaoException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private OffsetDateTime data;

    @Enumerated(EnumType.STRING)
    private TopicoStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Resposta> respostas = new ArrayList<>();


    public Topico(Usuario usuario, Curso curso, DadosCadastroTopicoDto dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00"));
        this.usuario = usuario;
        this.curso = curso;

    }

    public Topico(Usuario usuario, Usuario curso, DadosCadastroTopicoDto dados) {
    }

    public void atualizar(DadosAtualizacaoTopicoDTO dados) {
        if (!this.usuario.getId().equals(dados.usuario_id()) && !dados.senha().equals(this.usuario.getSenha())) {
            throw new ValidacaoException("Não é possível alterar tópicos que não são seus!");
        }
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }

        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }

        if (dados.curso_id() != null) {
            this.curso.setId(dados.curso_id());
        }

        if (dados.status() != null) {
            this.status = dados.status();
        }
    }

}
