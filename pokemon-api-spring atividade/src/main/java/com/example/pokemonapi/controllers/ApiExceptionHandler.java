package com.example.pokemonapi.controllers;

import com.example.pokemonapi.services.PokeApiService.PokemonNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PokemonNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratarPokemonNaoEncontrado(
            PokemonNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", exception.getMessage()));
    }
}
