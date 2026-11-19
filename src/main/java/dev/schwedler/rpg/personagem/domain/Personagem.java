package dev.schwedler.rpg.personagem.domain;

import dev.schwedler.rpg.classe.domain.Classe;
import dev.schwedler.rpg.ficha.domain.Ficha;
import dev.schwedler.rpg.item.domain.Item;
import dev.schwedler.rpg.missao.domain.Missao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personagem")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private BigDecimal nivel;
    @Generated(event = EventType.INSERT)
    @Column(name = "criado_em", updatable = false, insertable = false)
    private LocalDateTime criadoEm;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe classe;
    @OneToOne(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Ficha ficha;
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
    @ManyToMany(mappedBy = "personagens", fetch = FetchType.LAZY)
    private Set<Missao> missoes = new HashSet<>();
}
