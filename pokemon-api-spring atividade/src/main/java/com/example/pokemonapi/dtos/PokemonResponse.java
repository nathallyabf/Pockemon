package com.example.pokemonapi.dtos;

public record PokemonResponse(
        Long id,
        Integer idPokeApi,
        String nome,
        Integer altura,
        Integer peso,
        String tipo,
        Long treinadorId,
        String treinadorNome
) {
}
