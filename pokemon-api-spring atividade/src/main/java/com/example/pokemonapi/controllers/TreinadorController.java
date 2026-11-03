package com.example.pokemonapi.controllers;

import com.example.pokemonapi.dtos.TreinadorRequest;
import com.example.pokemonapi.dtos.TreinadorResponse;
import com.example.pokemonapi.services.TreinadorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treinadores")
public class TreinadorController {

    private final TreinadorService service;

    public TreinadorController(TreinadorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TreinadorResponse criar(@RequestBody TreinadorRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<TreinadorResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TreinadorResponse buscar(@PathVariable Long id) {
        var treinador = service.buscarPorId(id);
        return new TreinadorResponse(treinador.getId(), treinador.getNome());
    }
}
