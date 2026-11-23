package dev.schwedler.rpg.habilidade.service;

import dev.schwedler.rpg.exception.NotFoundException;
import dev.schwedler.rpg.habilidade.domain.Habilidade;
import dev.schwedler.rpg.habilidade.mapper.IHabilidadeMapper;
import dev.schwedler.rpg.habilidade.repository.IHabilidadeRepository;
import dev.schwedler.rpg.habilidade.request.HabilidadeRequest;
import dev.schwedler.rpg.habilidade.response.HabilidadeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabilidadeService {

    private final IHabilidadeRepository habilidadeRepository;
    private final IHabilidadeMapper habilidadeMapper;

    @Transactional
    public HabilidadeResponse save(HabilidadeRequest request) {
        Habilidade habilidadeDomain = habilidadeRepository.save(habilidadeMapper.toDomain(request));
        return habilidadeMapper.toResponse(habilidadeDomain);
    }

    @Transactional(readOnly = true)
    public Page<HabilidadeResponse> findAllPageable(Pageable pageable) {
        return habilidadeRepository.findAll(pageable).map(habilidadeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public List<HabilidadeResponse> findAllByParam(String tipo) {
        return habilidadeRepository.findAllByTipoIgnoreCase(tipo).stream().map(habilidadeMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public HabilidadeResponse findById(Integer id) {
        Habilidade habilidadeDomain =  habilidadeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Habilidade nao encontrada")
        );
        return habilidadeMapper.toResponse(habilidadeDomain);
    }

    @Transactional
    public HabilidadeResponse update(HabilidadeRequest request, Integer id){
        Habilidade habilidadeDomain =  habilidadeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Habilidade nao encontrada")
        );
        habilidadeMapper.updateDomain(request,habilidadeDomain);
        habilidadeRepository.save(habilidadeDomain);
        return habilidadeMapper.toResponse(habilidadeDomain);
    }

    @Transactional
    public void delete(Integer id){
        Habilidade habilidadeDomain =  habilidadeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Habilidade nao encontrada")
        );
        habilidadeRepository.deleteById(habilidadeDomain.getId());
    }


}
