package dev.schwedler.rpg.jogador.domain;

import dev.schwedler.rpg.ficha.domain.Ficha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jogador")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Generated(event = EventType.INSERT)
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;
    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    private List<Ficha> fichas = new ArrayList<>();
}
