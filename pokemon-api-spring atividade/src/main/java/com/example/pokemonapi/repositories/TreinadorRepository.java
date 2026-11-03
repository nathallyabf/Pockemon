package com.example.pokemonapi.repositories;

import com.example.pokemonapi.entities.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
}
