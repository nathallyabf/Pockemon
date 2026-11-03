package com.example.pokemonapi.services;

import com.example.pokemonapi.dtos.TreinadorRequest;
import com.example.pokemonapi.dtos.TreinadorResponse;
import com.example.pokemonapi.entities.Treinador;
import com.example.pokemonapi.repositories.TreinadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TreinadorService {

    private final TreinadorRepository repository;

    public TreinadorService(TreinadorRepository repository) {
        this.repository = repository;
    }

    public TreinadorResponse criar(TreinadorRequest request) {
        if (request.nome() == null || request.nome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O nome do treinador é obrigatório.");
        }

        Treinador treinador = repository.save(new Treinador(request.nome()));
        return converter(treinador);
    }

    public List<TreinadorResponse> listar() {
        return repository.findAll().stream()
                .map(this::converter)
                .toList();
    }

    public Treinador buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Treinador não encontrado."));
    }

    private TreinadorResponse converter(Treinador treinador) {
        return new TreinadorResponse(treinador.getId(), treinador.getNome());
    }
}
