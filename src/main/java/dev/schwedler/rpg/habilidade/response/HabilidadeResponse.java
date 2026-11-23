package dev.schwedler.rpg.habilidade.response;

import java.math.BigDecimal;

public record HabilidadeResponse(
        Integer id,
        String nome,
        String tipo,
        BigDecimal custoMana
) {
}
