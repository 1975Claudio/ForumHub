package br.com.alura.ForumHub.controller.dominio.resposta;

import br.com.alura.ForumHub.controller.dominio.resposta.dto.DadosCriacaoRespostaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

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

    public Resposta(Topico topico, Usuario usuario, DadosCriacaoRespostaDTO dados){
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.data = LocalDateTime.now().atOffset(ZoneOffset.of("-03:00"));
        this.usuario = usuario;
    }
}
