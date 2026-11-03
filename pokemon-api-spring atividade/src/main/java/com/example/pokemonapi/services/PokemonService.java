package com.example.pokemonapi.services;

import com.example.pokemonapi.dtos.PokemonResponse;
import com.example.pokemonapi.entities.Pokemon;
import com.example.pokemonapi.entities.Treinador;
import com.example.pokemonapi.repositories.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final TreinadorService treinadorService;
    private final PokeApiService pokeApiService;

    public PokemonService(PokemonRepository pokemonRepository,
                          TreinadorService treinadorService,
                          PokeApiService pokeApiService) {
        this.pokemonRepository = pokemonRepository;
        this.treinadorService = treinadorService;
        this.pokeApiService = pokeApiService;
    }

    public PokemonResponse consultarNaPokeApi(String nome) {
        PokeApiService.PokemonApi dados = pokeApiService.buscarPokemon(nome);
        return new PokemonResponse(
                null, dados.id(), dados.name(), dados.height(), dados.weight(),
                obterTipos(dados), null, null
        );
    }

    public PokemonResponse capturar(String nome, Long treinadorId) {
        Treinador treinador = treinadorService.buscarPorId(treinadorId);
        PokeApiService.PokemonApi dados = pokeApiService.buscarPokemon(nome);

        Pokemon pokemon = new Pokemon(
                dados.id(),
                dados.name(),
                dados.height(),
                dados.weight(),
                obterTipos(dados),
                treinador
        );

        return converter(pokemonRepository.save(pokemon));
    }

    public List<PokemonResponse> listar(Long treinadorId) {
        List<Pokemon> pokemons = treinadorId == null
                ? pokemonRepository.findAll()
                : pokemonRepository.findByTreinadorId(treinadorId);

        return pokemons.stream().map(this::converter).toList();
    }

    public PokemonResponse buscarPorId(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pokémon não encontrado."));
        return converter(pokemon);
    }

    public void excluir(Long id) {
        if (!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pokémon não encontrado.");
        }
        pokemonRepository.deleteById(id);
    }

    private String obterTipos(PokeApiService.PokemonApi dados) {
        if (dados.types() == null) {
            return "Não informado";
        }
        return dados.types().stream()
                .map(slot -> slot.type().name())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Não informado");
    }

    private PokemonResponse converter(Pokemon pokemon) {
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getIdPokeApi(),
                pokemon.getNome(),
                pokemon.getAltura(),
                pokemon.getPeso(),
                pokemon.getTipo(),
                pokemon.getTreinador().getId(),
                pokemon.getTreinador().getNome()
        );
    }
}
