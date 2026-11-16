package dev.schwedler.rpg.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message
) {
}
