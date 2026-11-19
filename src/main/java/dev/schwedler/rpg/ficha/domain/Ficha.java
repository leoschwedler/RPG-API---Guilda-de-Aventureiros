package dev.schwedler.rpg.ficha.domain;

import dev.schwedler.rpg.jogador.domain.Jogador;
import dev.schwedler.rpg.personagem.domain.Personagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ficha")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String destreza;
    private BigDecimal inteligencia;
    @Column(name = "vida_maxima")
    private BigDecimal vidaMaxima;
    @Column(name = "mana_maxima")
    private BigDecimal manaMaxima;
    @Generated(event = EventType.INSERT)
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador")
    private Jogador jogador;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personagem")
    private Personagem personagem;
}
