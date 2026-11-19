package dev.schwedler.rpg.jogador.repository;

import dev.schwedler.rpg.jogador.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJogadorRepository extends JpaRepository<Jogador, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);
}
