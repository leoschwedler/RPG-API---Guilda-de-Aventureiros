package dev.schwedler.rpg.missao.domain;

import dev.schwedler.rpg.personagem.domain.Personagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "missao")
public class Missao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    @Column(name = "nivel_minimo")
    private BigDecimal nivelMinimo;
    @Column(name = "recompensa_ouro")
    private BigDecimal recompensaOuro;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "missao_personagem",
            joinColumns = @JoinColumn(name = "id_missao"),
            inverseJoinColumns = @JoinColumn(name = "id_personagem")
    )
    private Set<Personagem> personagens = new HashSet<>();
}
