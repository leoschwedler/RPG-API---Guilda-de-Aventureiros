package dev.schwedler.rpg.habilidade.repository;

import dev.schwedler.rpg.habilidade.domain.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHabilidadeRepository extends JpaRepository<Habilidade, Integer> {

    List<Habilidade> findAllByTipoIgnoreCase(String tipo);
}
