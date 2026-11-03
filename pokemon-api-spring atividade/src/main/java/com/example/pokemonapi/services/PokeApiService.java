package com.example.pokemonapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class PokeApiService {

    private final RestClient client;

    public PokeApiService(RestClient.Builder builder) {
        this.client = builder
                .baseUrl("https:
                .build();
    }

    public record PokemonApi(
            Integer id,
            String name,
            Integer height,
            Integer weight,
            List<TypeSlot> types
    ) {
    }

    public record TypeSlot(TypeInfo type) {
    }

    public record TypeInfo(String name) {
    }

    public PokemonApi buscarPokemon(String nome) {
        try {
            return client.get()
                    .uri("/pokemon/{nome}", nome.toLowerCase().trim())
                    .retrieve()
                    .body(PokemonApi.class);
        } catch (RestClientException exception) {
            throw new PokemonNaoEncontradoException(
                    "Não foi possível encontrar o Pokémon: " + nome
            );
        }
    }

    public static class PokemonNaoEncontradoException extends RuntimeException {
        public PokemonNaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }
}
