package com.example.pokemonapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idPokeApi;

    private String nome;
    private Integer altura;
    private Integer peso;
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinador_id", nullable = false)
    private Treinador treinador;

    public Pokemon() {
    }

    public Pokemon(Integer idPokeApi, String nome, Integer altura,
                   Integer peso, String tipo, Treinador treinador) {
        this.idPokeApi = idPokeApi;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.tipo = tipo;
        this.treinador = treinador;
    }

    public Long getId() {
        return id;
    }

    public Integer getIdPokeApi() {
        return idPokeApi;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAltura() {
        return altura;
    }

    public Integer getPeso() {
        return peso;
    }

    public String getTipo() {
        return tipo;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }
}
