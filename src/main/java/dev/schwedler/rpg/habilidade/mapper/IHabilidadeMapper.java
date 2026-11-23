package dev.schwedler.rpg.habilidade.mapper;

import dev.schwedler.rpg.habilidade.domain.Habilidade;
import dev.schwedler.rpg.habilidade.request.HabilidadeRequest;
import dev.schwedler.rpg.habilidade.response.HabilidadeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IHabilidadeMapper {

    Habilidade toDomain(HabilidadeRequest request);

    HabilidadeResponse toResponse(Habilidade domain);

    Habilidade updateDomain(HabilidadeRequest request, @MappingTarget Habilidade habilidade);

}
