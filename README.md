# RPG API - Guilda de Aventureiros

## Entidades

Reflete a migration `V1__create_initial_tables.sql`.

### Jogador
- id
- nome
- email (unico)
- dataNascimento
- criadoEm

### Habilidade
- id
- nome
- tipo (ex: FOGO, CURA, FISICA)
- custoMana

### Classe
- id
- habilidade (N:1, cada classe tem uma habilidade)
- nome

### Personagem
- id
- classe (N:1, obrigatoria)
- nome
- nivel
- criadoEm

### Ficha
- id
- jogador (N:1, um jogador pode ter varias fichas)
- personagem (1:1, um personagem tem uma unica ficha)
- destreza
- inteligencia
- vidaMaxima
- manaMaxima
- criadoEm

### Item
- id
- personagem (N:1, item pertence a um personagem)
- nome
- raridade
- peso

### Missao
- id
- titulo
- nivelMinimo
- recompensaOuro

### MissaoPersonagem (tabela de ligacao N:N)
- id
- missao
- personagem
- par (missao, personagem) unico

## Endpoints

Prefixo de todas as rotas: `/v1`

### Jogadores
| Metodo | Rota | Descricao |
|---|---|---|
| POST | `/v1/jogadores` | Cadastra um jogador (email nao pode repetir) |
| GET | `/v1/jogadores/{id}` | Busca um jogador por id |
| GET | `/v1/jogadores/{id}/fichas` | Lista as fichas do jogador |

### Habilidades
| Metodo | Rota | Descricao |
|---|---|---|
| POST | `/v1/habilidades` | Cadastra uma habilidade |
| GET | `/v1/habilidades` | Lista todas |
| GET | `/v1/habilidades/{tipo}` | Filtra por tipo |

### Classes
| Metodo | Rota | Descricao |
|---|---|---|
| POST | `/v1/classes` | Cadastra uma classe com o id da sua habilidade |
| GET | `/v1/classes` | Lista as classes com a habilidade |

### Personagens
| Metodo | Rota | Descricao |
|---|---|---|
| POST | `/v1/personagens` | Cria um personagem (nome, nivel e id da classe) |
| GET | `/v1/personagens/{id}` | Detalhes do personagem, com classe e habilidade |
| PUT | `/v1/personagens/{id}` | Atualiza nome e classe |
| POST | `/v1/personagens/{id}/itens` | Adiciona um item ao inventario do personagem |
| GET | `/v1/personagens/{id}/itens` | Lista o inventario |

### Fichas
| Metodo | Rota | Descricao |
|---|---|---|
| POST | `/v1/fichas` | Cria a ficha de um personagem para um jogador; um personagem nao pode ter duas fichas |
| GET | `/v1/fichas` | Lista todas as fichas com o nome do jogador e do personagem |
| GET | `/v1/fichas/page/{page}/size/{size}` | Mesma listagem, paginada, da mais nova para a mais antiga |
| DELETE | `/v1/fichas/{id}` | Remove a ficha |

### Itens
| Metodo | Rota | Descricao |
|---|---|---|
| GET | `/v1/itens` | Lista todos os itens |

### Missoes
| Metodo | Rota | Descricao |
|---|---|---|
| POST | `/v1/missoes` | Cadastra uma missao |
| POST | `/v1/missoes/{id}/personagens/{personagemId}` | Personagem aceita a missao (respeitando o nivel minimo e sem repetir o par) |
| GET | `/v1/missoes?page=0&size=10` | Lista paginada usando `Pageable` |
