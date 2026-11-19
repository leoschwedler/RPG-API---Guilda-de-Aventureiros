package dev.schwedler.rpg.jogador.response;


import dev.schwedler.rpg.ficha.response.FichaResponse;
import lombok.Builder;

import java.util.List;
@Builder
public record FichasJogadorResponse(
        JogadorResponse jogadorResponse,
        List<FichaResponse> fichas
) {
}
