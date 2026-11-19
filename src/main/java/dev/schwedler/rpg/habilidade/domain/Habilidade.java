package dev.schwedler.rpg.habilidade.domain;

import dev.schwedler.rpg.classe.domain.Classe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "habilidade")
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String tipo;
    @Column(name = "custo_mana")
    private BigDecimal custoMana;
    @OneToMany(mappedBy = "habilidade", cascade = CascadeType.ALL)
    private List<Classe> classes = new ArrayList<>();
}
