package dev.schwedler.rpg.habilidade.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record HabilidadeRequest(
        @NotBlank
        String nome,
        @NotBlank
        String tipo,
        @NotNull
        BigDecimal custoMana
) {
}
