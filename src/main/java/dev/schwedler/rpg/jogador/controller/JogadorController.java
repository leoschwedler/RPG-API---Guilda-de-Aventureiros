package dev.schwedler.rpg.jogador.controller;

import dev.schwedler.rpg.jogador.request.JogadorRequest;
import dev.schwedler.rpg.jogador.response.FichasJogadorResponse;
import dev.schwedler.rpg.jogador.response.JogadorResponse;
import dev.schwedler.rpg.jogador.service.JogadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<JogadorResponse> saveJogador(@Valid @RequestBody JogadorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorService.saveJogador(request));
    }

    @GetMapping
    public ResponseEntity<Page<JogadorResponse>> findAllPageable(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(jogadorService.findAllPageable(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JogadorResponse> findJogadorById(@PathVariable Integer id){
       return ResponseEntity.ok(jogadorService.findJogadorById(id));
    }

    @GetMapping(value = "/findFichasJogadorById/{id}")
    public ResponseEntity<FichasJogadorResponse> findFichasJogadorById(@PathVariable Integer id){
        return ResponseEntity.ok(jogadorService.findFichasJogadorById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<JogadorResponse> updateJogador(@Valid @RequestBody JogadorRequest request, @PathVariable Integer id){
        return ResponseEntity.ok(jogadorService.updateJogador(request,id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteJogador(@PathVariable Integer id){
        jogadorService.deleteJogador(id);
        return ResponseEntity.noContent().build();
    }


}
