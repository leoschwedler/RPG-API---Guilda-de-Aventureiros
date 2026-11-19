package dev.schwedler.rpg.classe.domain;

import dev.schwedler.rpg.habilidade.domain.Habilidade;
import dev.schwedler.rpg.personagem.domain.Personagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classe")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidade")
    private Habilidade habilidade;
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<Personagem> personagems = new ArrayList<>();
}
