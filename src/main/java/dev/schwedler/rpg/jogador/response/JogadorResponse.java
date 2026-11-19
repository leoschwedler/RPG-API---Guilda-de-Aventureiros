package dev.schwedler.rpg.jogador.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JogadorResponse(
        Integer id,
        String nome,
        String email,
        LocalDate dataNascimento,
        LocalDateTime criadoEm
) {
}
