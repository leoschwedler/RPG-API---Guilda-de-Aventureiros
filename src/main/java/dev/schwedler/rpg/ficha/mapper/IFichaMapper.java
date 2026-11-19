package dev.schwedler.rpg.ficha.mapper;

import dev.schwedler.rpg.ficha.domain.Ficha;
import dev.schwedler.rpg.ficha.response.FichaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFichaMapper {

    List<FichaResponse> toResponse(List<Ficha> fichas);
}
