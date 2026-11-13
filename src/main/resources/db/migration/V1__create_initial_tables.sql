CREATE TABLE jogador
(
    id              SERIAL PRIMARY KEY,
    nome            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    data_nascimento DATE,
    criado_em       TIMESTAMP DEFAULT NOW()
);

CREATE TABLE habilidade
(
    id         SERIAL PRIMARY KEY,
    nome       VARCHAR(255) NOT NULL,
    tipo       VARCHAR(255) NOT NULL,
    custo_mana NUMERIC
);

CREATE TABLE classe
(
    id            SERIAL PRIMARY KEY,
    id_habilidade INTEGER      NOT NULL REFERENCES habilidade (id),
    nome          VARCHAR(255) NOT NULL
);

CREATE TABLE personagem
(
    id        SERIAL PRIMARY KEY,
    id_classe INTEGER      NOT NULL REFERENCES classe (id),
    nome      VARCHAR(255) NOT NULL,
    nivel     NUMERIC      NOT NULL,
    criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE ficha
(
    id            SERIAL PRIMARY KEY,
    id_jogador    INTEGER      NOT NULL REFERENCES jogador (id),
    id_personagem INTEGER      NOT NULL UNIQUE REFERENCES personagem (id),
    destreza      VARCHAR(255) NOT NULL,
    inteligencia  NUMERIC      NOT NULL,
    vida_maxima   NUMERIC      NOT NULL,
    mana_maxima   NUMERIC      NOT NULL,
    criado_em     TIMESTAMP DEFAULT NOW()
);

CREATE TABLE item
(
    id            SERIAL PRIMARY KEY,
    id_personagem INTEGER      NOT NULL REFERENCES personagem (id),
    nome          VARCHAR(255) NOT NULL,
    raridade      VARCHAR(255) NOT NULL,
    peso          NUMERIC      NOT NULL
);


CREATE TABLE missao
(
    id              SERIAL PRIMARY KEY,
    titulo          VARCHAR(255) NOT NULL,
    nivel_minimo    NUMERIC      NOT NULL,
    recompensa_ouro NUMERIC      NOT NULL
);

CREATE TABLE missao_personagem
(
    id            SERIAL PRIMARY KEY,
    id_missao     INTEGER NOT NULL REFERENCES missao (id),
    id_personagem INTEGER NOT NULL REFERENCES personagem (id),
    UNIQUE (id_missao, id_personagem)
);



