package dev.schwedler.rpg.habilidade.controller;

import dev.schwedler.rpg.habilidade.request.HabilidadeRequest;
import dev.schwedler.rpg.habilidade.response.HabilidadeResponse;
import dev.schwedler.rpg.habilidade.service.HabilidadeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/habilidades")
public class HabilidadeController {

    private final HabilidadeService service;

    @PostMapping
    public ResponseEntity<HabilidadeResponse> save(@Valid @RequestBody HabilidadeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<HabilidadeResponse>> findAllPageable(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
        return ResponseEntity.ok(service.findAllPageable(pageable));
    }

    @GetMapping(params = "tipo")
    public ResponseEntity<List<HabilidadeResponse>> findAllByParam(@RequestParam String tipo){
        return ResponseEntity.ok(service.findAllByParam(tipo));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HabilidadeResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HabilidadeResponse> update(@Valid @RequestBody HabilidadeRequest request, @PathVariable Integer id){
        return ResponseEntity.ok(service.update(request,id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
