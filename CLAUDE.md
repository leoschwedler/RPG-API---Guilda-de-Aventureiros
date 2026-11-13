# RPG API

Projeto de estudos (Spring Boot, Flyway, PostgreSQL). Nao implementar codigo
alem do que for pedido; o usuario quer aprender fazendo.

## Fluxo de git

Sempre que for commitar:

1. Nunca commitar direto na `main`. Antes do commit, criar uma branch da
   feature em andamento, a partir da `main`, no formato `<tipo>/<assunto>`
   (ex: `feat/migrations`, `feat/jogador-endpoints`, `fix/ficha-unique`).
2. Fazer o commit usando Conventional Commits:
   `<tipo>(<escopo opcional>): <descricao no imperativo, minuscula>`
   Tipos: `feat`, `fix`, `docs`, `refactor`, `test`, `chore`, `build`.
3. O repositorio git fica na pasta pai e compartilha arquivos de outros
   projetos (academia, mangahub). Commitar apenas os arquivos do rpg,
   passando os caminhos explicitamente. Nunca usar `git add -A` nem `git commit -a`.
4. Nao abrir PR nem dar push: o usuario faz isso pelo git.
