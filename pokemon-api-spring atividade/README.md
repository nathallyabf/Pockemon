# API de Pokémon e Treinadores

Projeto Java 21 + Spring Boot que consulta a [PokéAPI](https://pokeapi.co/) e salva Pokémon capturados, relacionando cada Pokémon a um treinador.

## Requisitos da atividade atendidos

- Repositório `PokemonRepository`.
- Repositório `TreinadorRepository`.
- Relacionamento `Treinador 1:N Pokemon` e `Pokemon N:1 Treinador`.
- Consulta de Pokémon na PokéAPI.
- Persistência dos Pokémon capturados usando Spring Data JPA.

## Como executar

1. Abra a pasta `pokemon-api-spring` na sua IDE.
2. Confira se o projeto está usando Java 21.
3. Execute `PokemonApiApplication`.
4. A API ficará disponível em `http://localhost:8080`.

Também é possível executar pelo terminal, dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

É necessária conexão com a internet para consultar a PokéAPI.

## Testando no Postman ou Insomnia

### 1. Criar treinadores

**POST** `http://localhost:8080/api/treinadores`

Body (JSON):

```json
{
  "nome": "Ash Ketchum"
}
```

Crie também outros treinadores, por exemplo Misty e Brock. A resposta informa o `id` criado.

### 2. Listar treinadores

**GET** `http://localhost:8080/api/treinadores`

### 3. Consultar diferentes Pokémon na PokéAPI

**GET** `http://localhost:8080/api/pokemon/catalogo/pikachu`

Outros exemplos:

- `/api/pokemon/catalogo/charizard`
- `/api/pokemon/catalogo/bulbasaur`
- `/api/pokemon/catalogo/squirtle`
- `/api/pokemon/catalogo/gengar`
- `/api/pokemon/catalogo/lucario`

### 4. Capturar um Pokémon para um treinador

**POST** `http://localhost:8080/api/pokemon/capturar?nome=pikachu&treinadorId=1`

Troque o nome e o ID para associar outros Pokémon a treinadores diferentes.

### 5. Listar todos os Pokémon capturados

**GET** `http://localhost:8080/api/pokemon`

### 6. Listar os Pokémon de um treinador específico

**GET** `http://localhost:8080/api/pokemon?treinadorId=1`

### 7. Buscar um Pokémon salvo pelo ID local

**GET** `http://localhost:8080/api/pokemon/1`

### 8. Excluir um Pokémon salvo

**DELETE** `http://localhost:8080/api/pokemon/1`

## Entendendo o relacionamento

- `Treinador` possui `@OneToMany`: um treinador pode ter vários Pokémon.
- `Pokemon` possui `@ManyToOne`: vários Pokémon podem apontar para o mesmo treinador.
- `@JoinColumn(name = "treinador_id")` cria a chave estrangeira na tabela `pokemons`.
- Os repositórios estendem `JpaRepository`, que já oferece métodos como `save`, `findAll`, `findById`, `existsById` e `deleteById`.

## Banco de dados

O projeto usa H2 em memória para facilitar a execução em aula. Para visualizar:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:pokemondb`
- User: `sa`
- Password: deixe em branco

Os dados são apagados quando a aplicação é encerrada.
