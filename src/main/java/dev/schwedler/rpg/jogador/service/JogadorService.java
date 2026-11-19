package dev.schwedler.rpg.jogador.service;

import dev.schwedler.rpg.exception.ConflictException;
import dev.schwedler.rpg.exception.NotFoundException;
import dev.schwedler.rpg.ficha.domain.Ficha;
import dev.schwedler.rpg.ficha.mapper.IFichaMapper;
import dev.schwedler.rpg.ficha.repository.IFichasRepository;
import dev.schwedler.rpg.jogador.domain.Jogador;
import dev.schwedler.rpg.jogador.mapper.IJogadorMapper;
import dev.schwedler.rpg.jogador.repository.IJogadorRepository;
import dev.schwedler.rpg.jogador.request.JogadorRequest;
import dev.schwedler.rpg.jogador.response.FichasJogadorResponse;
import dev.schwedler.rpg.jogador.response.JogadorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final IJogadorRepository iJogadorRepository;
    private final IJogadorMapper iJogadorMapper;
    private final IFichasRepository iFichasRepository;
    private final IFichaMapper iFichaMapper;

    public JogadorResponse saveJogador(JogadorRequest request) {
        if (iJogadorRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email ja utilizado");
        } else {
            Jogador jogadorDomain = iJogadorMapper.toDomain(request);
            iJogadorRepository.save(jogadorDomain);
            return iJogadorMapper.toResponse(jogadorDomain);
        }
    }

    public Page<JogadorResponse> findAllPageable(Pageable pageable) {
        return iJogadorRepository.findAll(pageable).map(iJogadorMapper::toResponse);
    }

    public JogadorResponse findJogadorById(Integer id) {
        Jogador jogadorDomain = iJogadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jogador nao encontrado"));
        return iJogadorMapper.toResponse(jogadorDomain);
    }

    public JogadorResponse updateJogador(JogadorRequest request, Integer id) {
        Jogador jogadorDomain = iJogadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jogador nao encontrado"));
        if (iJogadorRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new ConflictException("Email ja utilizado");
        } else {
            iJogadorMapper.updateDomain(request, jogadorDomain);
            iJogadorRepository.save(jogadorDomain);
            return iJogadorMapper.toResponse(jogadorDomain);
        }

    }

    public void deleteJogador(Integer id) {
        iJogadorRepository.deleteById(id);
    }


    public FichasJogadorResponse findFichasJogadorById(Integer id) {
        Jogador jogadorDomain = iJogadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jogador nao encontrado"));
        List<Ficha> listFichaDomainByJogador = iFichasRepository.findByJogadorId(jogadorDomain.getId());
        FichasJogadorResponse fichasJogadorResponse = FichasJogadorResponse.builder()
                .jogadorResponse(iJogadorMapper.toResponse(jogadorDomain))
                .fichas(iFichaMapper.toResponse(listFichaDomainByJogador))
                .build();
        return fichasJogadorResponse;
    }
}
