package dev.schwedler.rpg.jogador.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record JogadorRequest(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotNull
        LocalDate dataNascimento
) {
}
