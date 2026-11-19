package dev.schwedler.rpg.ficha.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FichaResponse(
        Integer id,
        String destreza,
        BigDecimal inteligencia,
        BigDecimal vidaMaxima,
        BigDecimal manaMaxima,
        LocalDateTime criadoEm
) {
}
