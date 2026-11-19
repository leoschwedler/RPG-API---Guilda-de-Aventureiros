package dev.schwedler.rpg.item.domain;

import dev.schwedler.rpg.personagem.domain.Personagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String raridade;
    private BigDecimal peso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personagem")
    private Personagem personagem;

}
