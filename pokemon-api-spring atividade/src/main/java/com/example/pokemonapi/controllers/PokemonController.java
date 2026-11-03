package com.example.pokemonapi.controllers;

import com.example.pokemonapi.dtos.PokemonResponse;
import com.example.pokemonapi.services.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/catalogo/{nome}")
    public PokemonResponse consultar(@PathVariable String nome) {
        return service.consultarNaPokeApi(nome);
    }

    @PostMapping("/capturar")
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonResponse capturar(@RequestParam String nome,
                                    @RequestParam Long treinadorId) {
        return service.capturar(nome, treinadorId);
    }

    @GetMapping
    public List<PokemonResponse> listar(
            @RequestParam(required = false) Long treinadorId) {
        return service.listar(treinadorId);
    }

    @GetMapping("/{id}")
    public PokemonResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
