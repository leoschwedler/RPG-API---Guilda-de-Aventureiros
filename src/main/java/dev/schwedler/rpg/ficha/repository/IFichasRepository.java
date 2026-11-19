package dev.schwedler.rpg.ficha.repository;

import dev.schwedler.rpg.ficha.domain.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFichasRepository extends JpaRepository<Ficha, Integer> {


    List<Ficha> findByJogadorId(Integer idJogador);
}
