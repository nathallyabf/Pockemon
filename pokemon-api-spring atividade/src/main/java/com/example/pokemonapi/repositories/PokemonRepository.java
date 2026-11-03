package com.example.pokemonapi.repositories;

import com.example.pokemonapi.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByTreinadorId(Long treinadorId);
}
