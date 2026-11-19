package dev.schwedler.rpg.jogador.mapper;

import dev.schwedler.rpg.jogador.domain.Jogador;
import dev.schwedler.rpg.jogador.request.JogadorRequest;
import dev.schwedler.rpg.jogador.response.FichasJogadorResponse;
import dev.schwedler.rpg.jogador.response.JogadorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IJogadorMapper {

    Jogador toDomain(JogadorRequest request);

    JogadorResponse toResponse(Jogador domain);

    FichasJogadorResponse toFichasJogadorResponse(Jogador domain);

    Jogador updateDomain(JogadorRequest request, @MappingTarget Jogador jogador);
}
