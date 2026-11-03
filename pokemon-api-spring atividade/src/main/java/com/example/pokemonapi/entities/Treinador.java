package com.example.pokemonapi.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "treinadores")
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "treinador")
    private List<Pokemon> pokemons = new ArrayList<>();

    public Treinador() {
    }

    public Treinador(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
