# Rotas da API

Relacionamentos do schema (`V1__create_initial_tables.sql`):

- **1:1:** `ficha` ↔ `personagem` (`id_personagem` UNIQUE)
- **1:N:** `jogador` → `ficha`, `personagem` → `item`, `classe` → `personagem`, `habilidade` → `classe`
- **N:N:** `missao` ↔ `personagem` (tabela `missao_personagem`)

## Jogador (`/jogadores`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/jogadores` | validação, 409 se o email já existir |
| GET | `/jogadores` | **paginação** (`Pageable`) |
| GET | `/jogadores/{id}` | 404 |
| PUT | `/jogadores/{id}` | atualização |
| DELETE | `/jogadores/{id}` | e se ele tiver fichas? |
| GET | `/jogadores/{id}/fichas` | **1:N** |

## Habilidade (`/habilidades`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/habilidades` | CRUD básico |
| GET | `/habilidades` | paginação |
| GET | `/habilidades?tipo=...` | filtro por parâmetro |
| GET | `/habilidades/{id}` | 404 |
| PUT | `/habilidades/{id}` | atualização |
| DELETE | `/habilidades/{id}` | remoção |

## Classe (`/classes`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/classes` | recebe `idHabilidade` e valida que ela existe |
| GET | `/classes` | listagem |
| GET | `/classes/{id}` | **N:1** com habilidade |
| PUT | `/classes/{id}` | atualização |
| DELETE | `/classes/{id}` | remoção |
| GET | `/classes/{id}/personagens` | **1:N** |

## Personagem (`/personagens`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/personagens` | recebe `idClasse` |
| GET | `/personagens` | paginação com filtro opcional por `nivel` ou `classe` |
| GET | `/personagens/{id}` | **projection** (nome, classe, nível) |
| PUT | `/personagens/{id}` | atualização |
| DELETE | `/personagens/{id}` | remoção |
| GET | `/personagens/{id}/itens` | **1:N** |
| GET | `/personagens/{id}/ficha` | **1:1** |
| GET | `/personagens/{id}/missoes` | **N:N** |

## Ficha (`/fichas`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/fichas` | recebe `idJogador` e `idPersonagem`; 409 se o personagem já tiver ficha (UNIQUE) |
| GET | `/fichas/{id}` | **1:1**, retorna jogador e personagem aninhados |
| PUT | `/fichas/{id}` | atualização |
| DELETE | `/fichas/{id}` | remoção |

## Item (`/itens`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/itens` | recebe `idPersonagem` |
| GET | `/itens?raridade=...` | filtro e paginação |
| GET | `/itens/{id}` | 404 |
| PUT | `/itens/{id}` | atualização |
| DELETE | `/itens/{id}` | remoção |
| PATCH | `/itens/{id}/transferir` | trocar o dono do item (corpo com `idPersonagem`) |

## Missão (`/missoes`)

| Método | Rota | Treina |
|---|---|---|
| POST | `/missoes` | CRUD |
| GET | `/missoes` | paginação |
| GET | `/missoes/{id}` | 404 |
| PUT | `/missoes/{id}` | atualização |
| DELETE | `/missoes/{id}` | remoção |
| POST | `/missoes/{id}/personagens/{idPersonagem}` | **N:N**: aceitar missão, 409 se já aceita, 400 se `nivel < nivel_minimo` |
| DELETE | `/missoes/{id}/personagens/{idPersonagem}` | abandonar missão |
| GET | `/missoes/{id}/personagens` | **N:N** do outro lado |
| GET | `/missoes/disponiveis?nivel=` | **native query** |

## Relatórios (`/relatorios`)

Foco em projections e native queries.

| Método | Rota | Ideia |
|---|---|---|
| GET | `/relatorios/ranking-personagens` | top personagens por nível (**native query** + paginação) |
| GET | `/relatorios/jogadores/{id}/resumo` | jogador com total de fichas e personagens (**projection** de interface) |
| GET | `/relatorios/personagens/{id}/peso-inventario` | `SUM(peso)` dos itens do personagem |
| GET | `/relatorios/missoes/populares` | missões com contagem de personagens (`GROUP BY`, **projection**) |
| GET | `/relatorios/classes/contagem` | personagens por classe |

## Ordem sugerida

1. **Jogador** (CRUD, já em andamento)
2. **Habilidade → Classe → Personagem** (N:1 em cadeia)
3. **Ficha** (1:1)
4. **Item** (1:N)
5. **Missão** (N:N)
6. **Relatórios** (projections e native queries)

## Dicas de modelagem

- `missao_personagem` tem `id` próprio: mapeie como `@ManyToMany` + `@JoinTable` (ignora o id) ou como entidade própria (melhor se ganhar colunas, como `data_aceite`).
- Nas listagens, retorne DTOs/projections em vez da entidade, para evitar N+1 e `LazyInitializationException`.
