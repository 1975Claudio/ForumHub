package br.com.alura.ForumHub.controller.dominio.resposta;

import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosCriacaoRespostaDto;
import br.com.alura.ForumHub.controller.dominio.topico.Topico;
import br.com.alura.ForumHub.controller.dominio.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Topico topico;

    private OffsetDateTime data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Resposta(Topico topico, Usuario usuario, DadosCriacaoRespostaDto dados){
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.data = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00"));
        this.usuario = usuario;
    }
}
